package ABC_Restaurant.example.ABC_Restaurant.dto.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    // Getters and Setters
}