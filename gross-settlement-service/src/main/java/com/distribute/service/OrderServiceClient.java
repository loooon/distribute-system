package com.distribute.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "order-service")
public interface OrderServiceClient {


  @RequestMapping(method = RequestMethod.GET, value = "/getByOrderId/{orderId}")
  String getByOrderId(@PathVariable("orderId") String orderId);

}
