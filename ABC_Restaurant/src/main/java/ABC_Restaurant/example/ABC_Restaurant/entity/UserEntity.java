package ABC_Restaurant.example.ABC_Restaurant.entity;

import ABC_Restaurant.example.ABC_Restaurant.enums.UserRole;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserStatus;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 255)
    private String firstName;
    @Column(length = 255)
    private String lastName;
    @Column(length = 255, unique = true, nullable = false)
    private String email;
    @Column(length = 255)
    private String mobileNumber;
    @Column(length = 255, nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
