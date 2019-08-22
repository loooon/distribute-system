package com.distribute;

import com.distribute.service.OrderServiceClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrossSettlementServiceController {

  /**
   * call by feign
   */
  @Autowired
  private OrderServiceClient orderServiceClient;

  /**
   * call by openFeign
   *
   * @param orderId orderId
   * @return value
   * HystrixCommand 这里使用HystrixCommand 注解配置熔断方法，当调用服务接口不可用时，调用本地方法 feignFallback
   */
  @LoadBalanced
  @HystrixCommand(fallbackMethod = "getByOrderIdFallback")
  @GetMapping("/getByOrderId/{orderId}")
  public String getByOrderId(@PathVariable("orderId") String orderId) {
    return orderServiceClient.getByOrderId(orderId);
  }

  public String getByOrderIdFallback(@PathVariable("orderId") String orderId) {
    return "result from gross-settlement service , " + orderId;
  }
}
