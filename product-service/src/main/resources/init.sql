CREATE DATABASE product_system_db /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
use product_system_db;

create table product_tb
(
  id       bigint primary key auto_increment,
  name     varchar(32)    null     default null comment '产品名称',
  price    decimal(19, 4) not null default 0.0000 comment '产品价格',
  category int            null     default 0 comment '产品分类'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '产品表';

create table product_inventory_tb
(
  id         bigint primary key auto_increment,
  product_id bigint not null comment '产品id',
  number     bigint not null default 0 comment '产品数量',
  unique key ukey_product_id (product_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '产品库存表';

