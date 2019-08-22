package com.distribute.controller;


import com.distribute.dto.OrderInfoDto;
import com.distribute.dto.ProductInfoDto;
import com.distribute.dto.ProjectIdNumberDto;
import com.distribute.response.ResponseMessage;
import com.distribute.service.internal.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RefreshScope
public class OrderController {

  @Autowired
  private OrderService orderService;

  @Value("${message}") // git配置文件里的key
  String message;

  @RequestMapping(value = "/hi")
  public String hi() {
    return message;
  }


  /**
   * call by openFeign
   *
   * @param projectIdNumberDto projectIdNumberDto
   * @return value
   * HystrixCommand 这里使用HystrixCommand 注解配置熔断方法，当调用服务接口不可用时，调用本地方法 feignFallback
   */
  @LoadBalanced
  @HystrixCommand(fallbackMethod = "reduceProductInventoryFallback")
  @PostMapping("/reduceProductInventory")
  public ResponseMessage<String> reduceProductInventory(@RequestBody ProjectIdNumberDto projectIdNumberDto) {
    return orderService.reduceProductInventory(projectIdNumberDto);
  }

  public ResponseMessage<String> reduceProductInventoryFallback(@RequestBody ProjectIdNumberDto projectIdNumberDto) {
    String message =
        "减少商品库存失败，请求参数:{'productId':" + projectIdNumberDto.getProductId()
            + ",'number':"
            + projectIdNumberDto.getNumber() + "}.";
    return new ResponseMessage<>(ResponseMessage.FAIL_CODE, message);
  }

  @LoadBalanced
  @HystrixCommand(fallbackMethod = "getInfoByProductIdFallBack")
  @GetMapping("/getInfoByProductId/{productId}")
  public ResponseMessage<ProductInfoDto> getInfoByProductId(@PathVariable("productId") Long productId) {
    return orderService.getInfoByProductId(productId);
  }

  public ResponseMessage<ProductInfoDto> getInfoByProductIdFallBack(@PathVariable("productId") Long productId) {
    String message = "查询商品信息失败，请求参数:{'productId':" + productId + "}.";
    return new ResponseMessage<ProductInfoDto>(ResponseMessage.FAIL_CODE, message);
  }


  /**
   * 订单接口
   *
   * @param productInfoDto 订单信息
   * @return 成功
   */
  @PostMapping("/addOrder")
  public ResponseMessage<String> addOrder(@RequestBody OrderInfoDto productInfoDto) {
    return orderService.addOrder(productInfoDto);
  }

  @PostMapping("/listOrderByUserId")
  public ResponseMessage<List<OrderInfoDto>> listOrderByUserId(@RequestParam("userId") Long userId) {
    return orderService.listOrderByUserId(userId);
  }

  @PostMapping("/updateOrderStatus")
  public ResponseMessage<String> updateOrderStatus(@RequestBody HashMap<String, Object> map) {
    return orderService.updateOrderStatus(((Integer) map.get("orderId")).longValue(), (Integer) map.get("status"));
  }
}
