package com.distribute.repository;

import com.distribute.pojo.ProductTb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface ProductRepository extends JpaRepository<ProductTb, Long> {

  @Query("select p from ProductTb p where p.name = ?1")
  List<ProductTb> findByName(String name);

  @Modifying
  @Query("update ProductTb p set p.name = ?1 where p.id = ?2")
  Integer setProductNameById(String name, Long id);

  @Query(value = "select p from ProductTb p where p.name =:name AND p.price=:price")
  List<ProductTb> findByMultiParam(@Param("name") String name, @Param("price") BigDecimal price);

  /**
   * 通过产品分类分页查询旅店以及城市的信息
   *
   * @param category 产品分类
   * @param pageable 分页信息
   * @return Page<Object [ ]>
   */
  @Query(value = "select t1.name as name,t2.number as number\n" +
      "from product_tb t1\n" +
      "  inner join product_inventory_tb t2 on t2.product_id = t1.id\n" +
      "where t1.category = :category",
      countQuery = "select count(*)" +
          "from product_tb t1 \n" +
          "  inner join product_inventory_tb t2 on t2.product_id = t1.id\n" +
          "where t1.category = :category"
      , nativeQuery = true)
  Page<Object[]> findByCategoryPageable(@Param("category") Integer category, Pageable pageable);
}