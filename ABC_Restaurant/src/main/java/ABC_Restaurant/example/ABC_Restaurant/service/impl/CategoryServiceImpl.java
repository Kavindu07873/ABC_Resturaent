package ABC_Restaurant.example.ABC_Restaurant.service.impl;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.CategoryRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CategoryResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.entity.CategoryEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.CustomerEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.UserEntity;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserRole;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserStatus;
import ABC_Restaurant.example.ABC_Restaurant.exception.AbcRestaurantException;
import ABC_Restaurant.example.ABC_Restaurant.repository.CategoryRepository;
import ABC_Restaurant.example.ABC_Restaurant.service.CategoryService;
import ABC_Restaurant.example.ABC_Restaurant.service.CustomerService;
import ABC_Restaurant.example.ABC_Restaurant.utill.FileService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ABC_Restaurant.example.ABC_Restaurant.constant.ApplicationConstant.*;

@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private FileService fileService;
    @Autowired
    private ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveNewCategory(CategoryRequestDTO categoryRequestDTO) {
        try {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(categoryRequestDTO.getName());
            categoryEntity.setDescription(categoryRequestDTO.getDescription());
            String attachment = saveAttachment(categoryRequestDTO);
            categoryEntity.setImage(attachment);
            categoryRepository.save(categoryEntity);
        } catch (Exception e) {
            log.error("Function saveNewCategory : {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<CategoryResponseDTO> loadAllCategory(int page, int size) {
        try {
            if (page < 0 || size < 0)
                throw new AbcRestaurantException(INVALID_ENTERED, "The page is not in a valid format.. ");
            Pageable pageable = PageRequest.of(page, size);
            Page<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable);
            // Correct the mapping logic here
            Page<CategoryResponseDTO> categoryResponseDTOS = categoryEntities.map(categoryEntity ->
                    modelMapper.map(categoryEntity, CategoryResponseDTO.class)
            );
            return categoryResponseDTOS;
        } catch (Exception e) {
            log.error("Function loadAllCategory : {}", e.getMessage());
            throw e;
        }
    }

    private String saveAttachment(CategoryRequestDTO categoryRequestDTO) {
        try {
            String imageUrl = "";
            if (categoryRequestDTO.getImage() != null) {
                imageUrl = fileService.saveMultipartFile(categoryRequestDTO.getImage(), categoryRequestDTO.getImage().getContentType());
            }
            return imageUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

}
