package ABC_Restaurant.example.ABC_Restaurant.service;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.CartRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CartResponseDTO;

import java.util.List;

public interface CartService {
    void addNewCart(CartRequestDTO cartRequestDTO);

    List<CartResponseDTO> findCartByUser();
}
