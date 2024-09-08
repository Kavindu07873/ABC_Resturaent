package ABC_Restaurant.example.ABC_Restaurant.repository;

import ABC_Restaurant.example.ABC_Restaurant.entity.AdminEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    @Query(value = "SELECT DISTINCT c FROM CartEntity c " +
            "LEFT JOIN UserEntity u ON c.userEntity = u " +
            "WHERE (c.userEntity.id=?1) GROUP BY c.id")
    List<CartEntity> findByUserId(long id);
}
