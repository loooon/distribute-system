package com.distribute.constant;

/**
 *
 */
public enum ApiExceptionCode {

  /**
   * 未找到资源
   */
  RESOURCE_NOT_FOUND(101, "Resource not found"), NOT_FOUND(404, "未找到");


  /**
   * 值
   */
  private Integer value;

  /**
   * 描述
   */
  private String desc;

  private ApiExceptionCode(Integer value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

}