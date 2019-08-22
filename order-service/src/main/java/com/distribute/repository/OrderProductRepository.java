package com.distribute.repository;

import com.distribute.pojo.OrderProductTb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProductTb, Long> {
  List<OrderProductTb> findByOrderIdIn(List<Long> orderIds);
}
