package com.distribute.service;

import com.distribute.dto.ProductInfoDto;
import com.distribute.dto.ProjectIdNumberDto;
import com.distribute.pojo.ProductInventoryTb;
import com.distribute.pojo.ProductTb;
import com.distribute.repository.ProductInventoryRepository;
import com.distribute.repository.ProductRepository;
import com.distribute.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductInventoryRepository productInventoryRepository;

  @Override
  public ResponseMessage<ProductInfoDto> getInfoByProductId(Long productId) {
    ResponseMessage<ProductInfoDto> responseMessage = new ResponseMessage<>();
    Optional<ProductTb> productTb = productRepository.findById(productId);
    if (!productTb.isPresent()) {
      return responseMessage;
    }
    ProductInfoDto dto = new ProductInfoDto();
    dto.setName(productTb.get().getName());
    dto.setPrice(productTb.get().getPrice().doubleValue());
    dto.setCategory(productTb.get().getCategory());
    dto.setProductId(productTb.get().getId());
    List<ProductInventoryTb> productInventoryTbs = productInventoryRepository.findAllByProductId(productTb.get().getId());
    if (!productInventoryTbs.isEmpty()) {
      dto.setNumber(productInventoryTbs.get(0).getNumber());
    }
    responseMessage.setContent(dto);
    return responseMessage;
  }

  @Override
  @Transactional
  public ResponseMessage<ProductInfoDto> addProduct(ProductInfoDto productInfoDto) {
    ProductTb productTb = new ProductTb();
    productTb.setName(productInfoDto.getName());
    productTb.setPrice(new BigDecimal(productInfoDto.getPrice()));
    productTb.setCategory(productInfoDto.getCategory());

    ProductTb save = productRepository.save(productTb);
    ProductInventoryTb productInventoryTb = new ProductInventoryTb();
    productInventoryTb.setNumber(productInfoDto.getNumber());
    productInventoryTb.setProductId(save.getId());
    productInventoryRepository.save(productInventoryTb);
    return getInfoByProductId(save.getId());
  }

  @Override
  @Transactional
  public ResponseMessage<String> deleteProductById(Long productId) {
    productRepository.deleteById(productId);
    productInventoryRepository.deleteByProductId(productId);
    return ResponseMessage.SUCCESS;
  }

  @Override
  public ResponseMessage<Page<Object[]>> findByCategoryPageable(Integer category, Pageable pageable) {
    Page<Object[]> productTbs = productRepository.findByCategoryPageable(category, pageable);
    return new ResponseMessage<>(productTbs);
  }

  @Override
  @Transactional
  public ResponseMessage<String> reduceProductInventory(ProjectIdNumberDto projectIdNumberDto) {
    productInventoryRepository.reduceProductInventory(projectIdNumberDto.getProductId(), projectIdNumberDto.getNumber());
    return ResponseMessage.SUCCESS;
  }
}
