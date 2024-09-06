package ABC_Restaurant.example.ABC_Restaurant.repository;

import ABC_Restaurant.example.ABC_Restaurant.entity.AdminEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
