package ABC_Restaurant.example.ABC_Restaurant.repository;

import ABC_Restaurant.example.ABC_Restaurant.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    @Query(value = "SELECT * FROM admin WHERE email= ?1 ", nativeQuery = true)
    Optional<AdminEntity> findByEmail(String email);
}
