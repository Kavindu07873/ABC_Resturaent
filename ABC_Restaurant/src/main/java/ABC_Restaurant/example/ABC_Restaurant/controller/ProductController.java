package ABC_Restaurant.example.ABC_Restaurant.controller;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.ProductRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.ProductResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.common.CommonResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAllProduct(@RequestParam(required = false,defaultValue = "0") Integer page,
                                            @RequestParam(required = false ,defaultValue = "5") Integer limit) {
        // Default values if page or size are not provided
        int pageNum = (page != null) ? page : 0; // Default to page 0
        int pageSize = (limit != null) ? limit : 10; // Default to size 10

        Page<ProductResponseDTO> productResponseDTOS = productService.loadAllProducts(pageNum, pageSize);
        return new ResponseEntity<>(new CommonResponseDTO(true, productResponseDTOS), HttpStatus.OK);
    }


    @PostMapping(value = "")
    public ResponseEntity registerNewProduct(@ModelAttribute ProductRequestDTO productRequestDTO) {
        System.out.println("Test 1");
        productService.saveNewProduct(productRequestDTO);
        return new ResponseEntity<>(new CommonResponseDTO(true, "Product Saved."), HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findProductByCategory(@RequestParam(required = false,defaultValue = "0") long category) {
        List<ProductResponseDTO> productResponseDTOS = productService.loadProductsByCategory(category);
        return new ResponseEntity<>(new CommonResponseDTO(true, productResponseDTOS), HttpStatus.OK);
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findProductById(@PathVariable(required = false) long productId) {
        ProductResponseDTO productResponseDTOS = productService.findProductsById(productId);
        return new ResponseEntity<>(new CommonResponseDTO(true, productResponseDTOS), HttpStatus.OK);
    }
}
