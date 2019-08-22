package com.distribute.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author michael
 */
public class OrderUtil {
  public static String generateSerialNo() {
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    return new SimpleDateFormat("yyyyMMddhhmmssSS").format(new Date()) + uuid;
  }
}
