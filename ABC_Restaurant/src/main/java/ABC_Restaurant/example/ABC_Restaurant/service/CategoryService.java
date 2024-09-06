package ABC_Restaurant.example.ABC_Restaurant.service;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.CategoryRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CategoryResponseDTO;
import org.springframework.data.domain.Page;

public interface CategoryService {
    void saveNewCategory(CategoryRequestDTO categoryRequestDTO);

    Page<CategoryResponseDTO> loadAllCategory(int page, int size);
}
