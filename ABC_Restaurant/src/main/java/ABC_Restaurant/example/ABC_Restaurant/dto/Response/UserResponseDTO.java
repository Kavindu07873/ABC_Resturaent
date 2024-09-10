package ABC_Restaurant.example.ABC_Restaurant.dto.Response;

import ABC_Restaurant.example.ABC_Restaurant.enums.UserRole;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDTO {
    private long id;
    private String username;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String password;
    private UserStatus status;
    private UserRole userRole;
}
