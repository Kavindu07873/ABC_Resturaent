package ABC_Restaurant.example.ABC_Restaurant.controller;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.CartRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Request.ProductRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CartResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CategoryResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.common.CommonResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.service.CartService;
import ABC_Restaurant.example.ABC_Restaurant.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(ProductService productService, CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerNewProduct(@RequestBody CartRequestDTO cartRequestDTO) {
        System.out.println("Test 2");
        cartService.addNewCart(cartRequestDTO);
        return new ResponseEntity<>(new CommonResponseDTO(true, "Cart Saved."), HttpStatus.OK);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCartByUser() {
        List<CartResponseDTO> cartResponseDTOS = cartService.findCartByUser();
        return new ResponseEntity<>(new CommonResponseDTO(true, cartResponseDTOS), HttpStatus.OK);
    }
}
