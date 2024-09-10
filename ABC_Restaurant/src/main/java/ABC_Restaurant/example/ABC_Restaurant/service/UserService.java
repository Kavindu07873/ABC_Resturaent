package ABC_Restaurant.example.ABC_Restaurant.service;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.RegisterRequest;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.UserResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.UserDTO;
import ABC_Restaurant.example.ABC_Restaurant.entity.UserEntity;

public interface UserService {
    UserEntity getUserDetailsForLogin(String username);

    void saveNewUser(RegisterRequest registerRequest) ;

    String userLogin(RegisterRequest registerRequest);

    UserResponseDTO findProfile();
}
