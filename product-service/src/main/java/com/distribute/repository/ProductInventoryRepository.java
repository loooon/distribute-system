package com.distribute.repository;

import com.distribute.pojo.ProductInventoryTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author michael
 */
public interface ProductInventoryRepository extends JpaRepository<ProductInventoryTb, Long> {

  /**
   * @param productId 商品id
   * @param number    减少库存数量
   * @return 返回更新数量
   */
  @Modifying
  @Query(value = "update product_inventory_tb pi set pi.number = pi.number - ?2 where pi.product_id = ?1 "
      + "and pi.number >= ?2", nativeQuery = true)
  Integer reduceProductInventory(Long productId, Long number);

  @Query(value = "select pi from ProductInventoryTb pi where pi.productId =:productId")
  List<ProductInventoryTb> findAllByProductId(@Param("productId") Long productId);

  @Modifying
  @Query(name = "delete * from product_inventory_tb pi where pi.product_id = ?1", nativeQuery = true)
  void deleteByProductId(Long productId);
}
