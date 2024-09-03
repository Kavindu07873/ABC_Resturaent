package ABC_Restaurant.example.ABC_Restaurant.service;

import ABC_Restaurant.example.ABC_Restaurant.dto.UserDTO;

public interface UserService {
    UserDTO getUserDetailsForLogin(String username);
}
