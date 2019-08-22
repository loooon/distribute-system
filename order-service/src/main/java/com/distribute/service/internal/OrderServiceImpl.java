package com.distribute.service.internal;

import com.distribute.constant.Constant;
import com.distribute.dto.OrderInfoDto;
import com.distribute.dto.ProductInfoDto;
import com.distribute.dto.ProjectIdNumberDto;
import com.distribute.exception.ApiException;
import com.distribute.pojo.BaseEntity;
import com.distribute.pojo.OrderProductTb;
import com.distribute.pojo.OrderTb;
import com.distribute.repository.OrderProductRepository;
import com.distribute.repository.OrderRepository;
import com.distribute.response.ResponseMessage;
import com.distribute.service.external.ProductServiceClient;
import com.distribute.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderProductRepository orderProductRepository;

  @Autowired
  private ProductServiceClient productServiceClient;

  @Override
  public ResponseMessage<String> reduceProductInventory(ProjectIdNumberDto projectIdNumberDto) {
    return productServiceClient.reduceProductInventory(projectIdNumberDto);
  }

  @Override
  public ResponseMessage<ProductInfoDto> getInfoByProductId(Long productId) {
    return productServiceClient.getInfoByProductId(productId);
  }

  @Override
  @Transactional
  public ResponseMessage<String> addOrder(OrderInfoDto productInfoDto) {

    List<ProjectIdNumberDto> productInfoDtos = productInfoDto.getProjectIdNumberDtos();
    // 所有产品数量都充足
    boolean allProductSufficient = productInfoDtos
        .stream()
        .allMatch(a -> checkIfProductInventory(a.getProductId(),
            a.getNumber()));
    if (!allProductSufficient) {
      throw new ApiException("产品数量不足，请重新下单");
    }

    OrderTb orderTb = new OrderTb();
    orderTb.setAmount(new BigDecimal(productInfoDto.getAmount()).setScale(Constant.TWO, RoundingMode.HALF_UP));
    orderTb.setSerialNo(OrderUtil.generateSerialNo());
    orderTb.setName(productInfoDto.getName());
    orderTb.setStatus(Constant.ZERO);
    orderTb.setUserId(productInfoDto.getUserId());
    final OrderTb orderTb1 = orderRepository.save(orderTb);
    List<OrderProductTb> orderProducts = productInfoDtos.stream().map(item -> {
      Long orderId = orderTb1.getId();
      OrderProductTb orderProductTb = new OrderProductTb();
      orderProductTb.setOrderId(orderId);
      orderProductTb.setProductId(item.getProductId());
      orderProductTb.setNumber(item.getNumber());
      return orderProductTb;
    }).collect(Collectors.toList());
    orderProductRepository.saveAll(orderProducts);

    // 减少库存
    productInfoDtos.stream().forEach(item -> {
      productServiceClient.reduceProductInventory(item);
    });


    return ResponseMessage.SUCCESS;
  }

  /**
   * 检查产品库存是否充足
   *
   * @return
   */
  private boolean checkIfProductInventory(Long productId, Long number) {
    ResponseMessage<ProductInfoDto> existProducts = productServiceClient.getInfoByProductId(productId);
    return existProducts.getContent().getNumber().compareTo(number) > Constant.ZERO;
  }

  @Override
  public ResponseMessage<List<OrderInfoDto>> listOrderByUserId(Long userId) {
    List<OrderTb> orderTbs = orderRepository.findByUserId(userId);
    ResponseMessage<List<OrderInfoDto>> responseMessage = new ResponseMessage<>();
    if (!orderTbs.isEmpty()) {
      List<Long> orderIds = orderTbs.stream().map(BaseEntity::getId).collect(Collectors.toList());
      List<OrderProductTb> orderProductInfo = orderProductRepository.findByOrderIdIn(orderIds);
      Map<Long, List<OrderProductTb>> orderIdProductInfoMap = orderProductInfo.stream()
          .collect(Collectors.groupingBy(OrderProductTb::getOrderId));

      List<OrderInfoDto> infoDtos = orderTbs.stream().map(a -> {
        Long orderId = a.getId();
        OrderInfoDto infoDto = new OrderInfoDto();
        infoDto.setAmount(a.getAmount().doubleValue());
        infoDto.setOrderId(orderId);
        infoDto.setCreateTime(a.getCreateTime());
        infoDto.setUpdateTime(a.getUpdateTime());
        infoDto.setName(a.getName());
        infoDto.setSerialNo(a.getSerialNo());
        infoDto.setStatus(a.getStatus());
        List<OrderProductTb> orderProductTbs = orderIdProductInfoMap.get(a.getId());
        List<ProjectIdNumberDto> projectIdNumberDtos = orderProductTbs.stream().map(item -> {
          ProjectIdNumberDto idNumberDto = new ProjectIdNumberDto();
          idNumberDto.setNumber(item.getNumber());
          idNumberDto.setProductId(item.getProductId());
          return idNumberDto;
        }).collect(Collectors.toList());
        infoDto.setProjectIdNumberDtos(projectIdNumberDtos);
        return infoDto;
      }).collect(Collectors.toList());
      responseMessage.setContent(infoDtos);
    }
    return responseMessage;
  }

  @Override
  @Transactional
  public ResponseMessage<String> updateOrderStatus(Long orderId, Integer status) {
    orderRepository.setStatusByOrderId(orderId, status);
    return ResponseMessage.SUCCESS;
  }
}
