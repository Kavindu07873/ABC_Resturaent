package ABC_Restaurant.example.ABC_Restaurant.controller;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.CategoryRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Request.OrderRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.common.CommonResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("/orders")
public class OrderController {

    @PostMapping(value = "/{cartId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerNewProduct(
            @PathVariable long cartId,
            @RequestBody OrderRequestDTO orderRequestDTO) {
        System.out.println("Test 2");
//        cartService.addNewCart(cartRequestDTO);

        System.out.println("cartId : "+cartId);
        System.out.println(orderRequestDTO.getShippingAddress().getCity());
        System.out.println(orderRequestDTO.getShippingAddress().getPhone());
        System.out.println(orderRequestDTO.getShippingAddress().getPostalCode());
        System.out.println(orderRequestDTO.getShippingAddress().getDetailedAddress());

        return new ResponseEntity<>(new CommonResponseDTO(true, "Cart Saved."), HttpStatus.OK);
    }

//    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> findCartByUser() {
//        List<CartResponseDTO> cartResponseDTOS = cartService.findCartByUser();
//        return new ResponseEntity<>(new CommonResponseDTO(true, cartResponseDTOS), HttpStatus.OK);
//    }

}
