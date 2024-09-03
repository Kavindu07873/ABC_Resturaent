package ABC_Restaurant.example.ABC_Restaurant.dto;

import ABC_Restaurant.example.ABC_Restaurant.enums.UserRole;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminDTO {
    private long id;
    private String  name;
    private String  email;
    private String  password;
    private UserRole userRole;
    private String  mobileNumber;
}
