package com.distribute.service.internal;

import com.distribute.dto.OrderInfoDto;
import com.distribute.dto.ProductInfoDto;
import com.distribute.dto.ProjectIdNumberDto;
import com.distribute.response.ResponseMessage;

import java.util.List;

public interface OrderService {

  public ResponseMessage<String> reduceProductInventory(ProjectIdNumberDto projectIdNumberDto);

  public ResponseMessage<ProductInfoDto> getInfoByProductId(Long productId);

  public ResponseMessage<String> addOrder(OrderInfoDto productInfoDto);

  public ResponseMessage<List<OrderInfoDto>> listOrderByUserId(Long userId);

  public ResponseMessage<String> updateOrderStatus(Long orderId, Integer status);
}
