package ABC_Restaurant.example.ABC_Restaurant.dto.Response;

import ABC_Restaurant.example.ABC_Restaurant.enums.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerResponseDTO {
    private String fullName;
    private String email;
    private String password;
    private UserRole userRole;

}
