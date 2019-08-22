package com.distribute.controller;

import com.distribute.dto.ProductInfoDto;
import com.distribute.dto.ProjectIdNumberDto;
import com.distribute.response.ResponseMessage;
import com.distribute.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author michael
 * @since v1.0.0
 */
@RestController
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping("/addProduct")
  public ResponseMessage<ProductInfoDto> addProduct(@RequestBody ProductInfoDto productInfoDto) {
    return productService.addProduct(productInfoDto);
  }

  @GetMapping("/deleteProductById/{productId}")
  public ResponseMessage<String> deleteProductById(@PathVariable("productId") Long productId) {
    return productService.deleteProductById(productId);
  }

  @PostMapping("/reduceProductInventory")
  public ResponseMessage<String> reduceProductInventory(@RequestBody ProjectIdNumberDto projectIdNumberDto) {
    return productService.reduceProductInventory(projectIdNumberDto);
  }

  @GetMapping("/getInfoByProductId/{productId}")
  public ResponseMessage<ProductInfoDto> getInfoByProductId(@PathVariable("productId") Long productId) {
    return productService.getInfoByProductId(productId);
  }

  @GetMapping("/findByCategoryPageable/{category}")
  public ResponseMessage<Page<Object[]>> findByCategoryPageable(@PathVariable("category") Integer category, Pageable pageable) {
    return productService.findByCategoryPageable(category, pageable);
  }
}
