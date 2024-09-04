package ABC_Restaurant.example.ABC_Restaurant.entity;

import ABC_Restaurant.example.ABC_Restaurant.enums.UserRole;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "staff")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 255)
    private String  name;
    @Column(length = 255, nullable = false, unique = true)
    private String  email;
    @Column(length = 255, nullable = false)
    private String  password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(length = 255, nullable = false)
    private String  mobileNumber;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
