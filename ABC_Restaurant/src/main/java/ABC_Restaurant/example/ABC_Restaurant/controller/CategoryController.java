package ABC_Restaurant.example.ABC_Restaurant.controller;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.CategoryRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CategoryResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CustomerResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.common.CommonResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.service.CategoryService;
import ABC_Restaurant.example.ABC_Restaurant.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewCategory(@ModelAttribute CategoryRequestDTO categoryRequestDTO) {
        System.out.println("Test 1");
        categoryService.saveNewCategory(categoryRequestDTO);
        return new ResponseEntity<>(new CommonResponseDTO(true, "You can proceed normal sign up process"), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllCustomer(@RequestParam int page,
                                             @RequestParam int size) {
        Page<CategoryResponseDTO> customerResonseDTOPage = categoryService.loadAllCategory(page, size);
        return new ResponseEntity<>(new CommonResponseDTO(true, customerResonseDTOPage), HttpStatus.OK);
    }

}
