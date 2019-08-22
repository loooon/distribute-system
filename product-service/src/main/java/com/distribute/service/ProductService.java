package com.distribute.service;

import com.distribute.dto.ProductInfoDto;
import com.distribute.dto.ProjectIdNumberDto;
import com.distribute.response.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author michael
 */
public interface ProductService {
  public ResponseMessage<ProductInfoDto> getInfoByProductId(Long productId);

  public ResponseMessage<ProductInfoDto> addProduct(ProductInfoDto productInfoDto);

  public ResponseMessage<String> deleteProductById(Long productId);

  public ResponseMessage<Page<Object[]>> findByCategoryPageable(Integer category, Pageable pageable);

  // 减少商品库存
  public ResponseMessage<String> reduceProductInventory(ProjectIdNumberDto projectIdNumberDto);

}
