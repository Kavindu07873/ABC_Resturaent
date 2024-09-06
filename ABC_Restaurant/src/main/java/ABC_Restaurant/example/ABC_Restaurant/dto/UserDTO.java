package ABC_Restaurant.example.ABC_Restaurant.dto;

import ABC_Restaurant.example.ABC_Restaurant.enums.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {
    private long id;
    private String  username;
    private String  lastName;
    private String  email;
    private String  mobileNumber;
    private String  password;
    private UserRole userRole;

}
