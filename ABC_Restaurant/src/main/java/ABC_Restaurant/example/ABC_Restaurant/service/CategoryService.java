package ABC_Restaurant.example.ABC_Restaurant.service;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.CategoryRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CategoryResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CustomerResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    void saveNewCategory(CategoryRequestDTO categoryRequestDTO);

    Page<CategoryResponseDTO> loadAllCategory(int page, int size );

    List<CategoryResponseDTO> loadAllCategoryList();

    CategoryResponseDTO findCategoryId(long categoryId);
}
