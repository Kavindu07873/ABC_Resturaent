package ABC_Restaurant.example.ABC_Restaurant.dto.Request;

import ABC_Restaurant.example.ABC_Restaurant.enums.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddNewUserRequestDTO {
    private String firstName;
    private String email;
    private String password;
    private UserRole userRole;
}
