package ABC_Restaurant.example.ABC_Restaurant.repository;

import ABC_Restaurant.example.ABC_Restaurant.entity.AdminEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    @Query(value = "SELECT * FROM staff WHERE email= ?1 ", nativeQuery = true)
    Optional<StaffEntity> findByEmail(String email);
}
