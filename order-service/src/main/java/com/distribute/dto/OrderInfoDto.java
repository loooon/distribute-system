package com.distribute.dto;

import java.util.Date;
import java.util.List;

public class OrderInfoDto {

  private Long orderId;

  private Long userId;

  private String name;

  private String serialNo;

  private Double amount;

  private Integer status;

  private Date createTime;

  private Date updateTime;

  private List<ProjectIdNumberDto> projectIdNumberDtos;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSerialNo() {
    return serialNo;
  }

  public void setSerialNo(String serialNo) {
    this.serialNo = serialNo;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public List<ProjectIdNumberDto> getProjectIdNumberDtos() {
    return projectIdNumberDtos;
  }

  public void setProjectIdNumberDtos(List<ProjectIdNumberDto> projectIdNumberDtos) {
    this.projectIdNumberDtos = projectIdNumberDtos;
  }
}
