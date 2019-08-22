package com.distribute.repository;

import com.distribute.pojo.OrderTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<OrderTb, Long> {

  List<OrderTb> findByUserId(Long userId);

  @Modifying
  @Query(value = "update OrderTb o set o.status = ?2 where o.id = ?1")
  int setStatusByOrderId(Long orderId, Integer status);
}