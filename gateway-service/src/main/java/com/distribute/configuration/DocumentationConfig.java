package com.distribute.configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

  @Override
  public List<SwaggerResource> get() {
    List resources = new ArrayList<>();
    resources.add(swaggerResource("订单服务接口", "/order-service/v2/api-docs", "1.0"));
    resources.add(swaggerResource("商品服务接口", "/product-service/v2/api-docs", "1.0"));
    return resources;
  }

  private SwaggerResource swaggerResource(String name, String location, String version) {
    SwaggerResource swaggerResource = new SwaggerResource();
    swaggerResource.setName(name);
    swaggerResource.setLocation(location);
    swaggerResource.setSwaggerVersion(version);
    return swaggerResource;
  }
}