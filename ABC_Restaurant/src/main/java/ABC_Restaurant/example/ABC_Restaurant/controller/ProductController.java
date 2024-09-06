package ABC_Restaurant.example.ABC_Restaurant.controller;

import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CustomerResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.ProductResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.common.CommonResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.service.CustomerService;
import ABC_Restaurant.example.ABC_Restaurant.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAllProduct(@RequestParam int page,
                                          @RequestParam int size) {
        Page<ProductResponseDTO> productResponseDTOS = productService.loadAllCustomer(page ,size);
        return new ResponseEntity<>(new CommonResponseDTO(true,productResponseDTOS ), HttpStatus.OK);
    }
}
