package ABC_Restaurant.example.ABC_Restaurant.service;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.RegisterRequest;
import ABC_Restaurant.example.ABC_Restaurant.dto.UserDTO;

public interface UserService {
    UserDTO getUserDetailsForLogin(String username);

    void saveNewUser(RegisterRequest registerRequest) ;

    String userLogin(RegisterRequest registerRequest);
}
