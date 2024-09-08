package ABC_Restaurant.example.ABC_Restaurant.repository;

import ABC_Restaurant.example.ABC_Restaurant.dto.Response.ProductResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    @Query(value = "SELECT DISTINCT p FROM ProductEntity p " +
            "LEFT JOIN CategoryEntity c ON p.categoryEntity = c " +
            "WHERE (c.id=?1) GROUP BY p.id")
    List<ProductEntity> findAllProductsByCategory(long category);


}
