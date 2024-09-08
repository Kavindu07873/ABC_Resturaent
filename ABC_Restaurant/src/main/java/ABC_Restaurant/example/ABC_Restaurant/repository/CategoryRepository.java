package ABC_Restaurant.example.ABC_Restaurant.repository;

import ABC_Restaurant.example.ABC_Restaurant.entity.CategoryEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query(value = "SELECT * from category where name=?1", nativeQuery = true)
    CategoryEntity findByName(String name);
}
