package com.distribute.pojo;

import javax.persistence.Entity;

/**
 * @author michael
 */
@Entity
public class OrderProductTb extends BaseEntity {

  private Long orderId;

  private Long productId;

  private Long number;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }
}
