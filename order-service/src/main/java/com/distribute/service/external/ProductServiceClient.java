package com.distribute.service.external;
// 内部的

import com.distribute.dto.ProductInfoDto;
import com.distribute.dto.ProjectIdNumberDto;
import com.distribute.response.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "product-service")
public interface ProductServiceClient {

  @PostMapping("/reduceProductInventory")
  public ResponseMessage<String> reduceProductInventory(@RequestBody ProjectIdNumberDto projectIdNumberDto);

  @GetMapping("/getInfoByProductId/{productId}")
  public ResponseMessage<ProductInfoDto> getInfoByProductId(@PathVariable("productId") Long productId);
}
