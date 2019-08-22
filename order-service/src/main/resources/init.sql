CREATE DATABASE product_system_db /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
use product_system_db;

create table order_tb
(
  id          bigint primary key auto_increment,
  user_id     bigint         not null comment '用户id',
  name        varchar(32)    null     default null comment '订单名称',
  serial_no   varchar(32)    null     default null comment '订单编号',
  amount      decimal(19, 4) not null default 0.0000 comment '订单金额',
  status      int            null     default 0 comment '订单状态，默认0，0:待支付,10:已支付 ,20:订单失败',
  create_time datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  unique key ukey_serial_no (serial_no)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '订单表';

create table order_product_tb
(
  id          bigint primary key auto_increment,
  order_id    bigint not null comment '订单id',
  product_id  bigint not null comment '订单产品id',
  number      bigint not null default 0 comment '订单产品数量',
  create_time datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  unique key ukey_product_id (product_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '订单产品表';



