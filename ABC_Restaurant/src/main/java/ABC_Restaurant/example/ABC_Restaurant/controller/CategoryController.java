package ABC_Restaurant.example.ABC_Restaurant.controller;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.CategoryRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CategoryResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.common.CommonResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(new CommonResponseDTO(true, "Successfully Saved Category"), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllCategory(@RequestParam(defaultValue = "0", required = false) int page,
                                             @RequestParam(defaultValue = "0", required = false) int size) {
        if (page != 0) {
            page = page - 1;
        }
        if (size != 0) {
            Page<CategoryResponseDTO> categoryResponseDTOS = categoryService.loadAllCategory(page, size);
            return new ResponseEntity<>(new CommonResponseDTO(true, categoryResponseDTOS), HttpStatus.OK);
        } else {
            List<CategoryResponseDTO> categoryResponseDTOS = categoryService.loadAllCategoryList();
            return new ResponseEntity<>(new CommonResponseDTO(true, categoryResponseDTOS), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCategoryByID(@PathVariable(required = false) long categoryId) {
        CategoryResponseDTO categoryResponseDTO = categoryService.findCategoryId(categoryId);
        return new ResponseEntity<>(new CommonResponseDTO(true, categoryResponseDTO), HttpStatus.OK);
    }
}
