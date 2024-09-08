package ABC_Restaurant.example.ABC_Restaurant.service.impl;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.ProductRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CategoryResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.ProductResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.entity.CategoryEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.ProductEntity;
import ABC_Restaurant.example.ABC_Restaurant.exception.AbcRestaurantException;
import ABC_Restaurant.example.ABC_Restaurant.repository.CategoryRepository;
import ABC_Restaurant.example.ABC_Restaurant.repository.ProductRepository;
import ABC_Restaurant.example.ABC_Restaurant.service.ProductService;
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
import java.util.stream.Collectors;

import static ABC_Restaurant.example.ABC_Restaurant.constant.ApplicationConstant.INVALID_ENTERED;
import static ABC_Restaurant.example.ABC_Restaurant.constant.ApplicationConstant.USER_NOT_FOUND;

@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private FileService fileService;
    @Autowired
    private ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductResponseDTO> loadAllProducts(int page, int size) {
        try {
            if (page < 0 || size < 0) {
                throw new AbcRestaurantException(INVALID_ENTERED, "The page or size is not in a valid format.");
            }

            Pageable pageable = PageRequest.of(page, size);

            // Fetch products from the repository
            Page<ProductEntity> productEntities = productRepository.findAll(pageable);

            // Map ProductEntity to ProductResponseDTO using ModelMapper
            Page<ProductResponseDTO> productResponseDTOS = productEntities.map(
                    product -> modelMapper.map(product, ProductResponseDTO.class)
            );

            return productResponseDTOS;

        } catch (Exception e) {
            log.error("Function loadAllProducts : {}", e.getMessage());
            throw e;
        }
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveNewProduct(ProductRequestDTO productRequestDTO) {
        try {
            ProductEntity productEntity = modelMapper.map(productRequestDTO, ProductEntity.class);
            productEntity.setMealName(productRequestDTO.getName());
            productEntity.setMealType(productRequestDTO.getCategory());
            if (productRequestDTO.getCategory() != null) {
                CategoryEntity categoryEntity = categoryRepository.findByName(productRequestDTO.getCategory());
                if (categoryEntity != null) {
                    productEntity.setCategoryEntity(categoryEntity);
                }
            }

            String imageUrl = saveAttachment(productRequestDTO);
            productEntity.setImage(imageUrl);
            productRepository.save(productEntity);
        } catch (Exception e) {
            log.error("Function saveNewProduct : {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<ProductResponseDTO> loadProductsByCategory(long category) {
        try {
            System.out.println("Category ID: " + category);

            // Fetch the list of product entities by category
            List<ProductEntity> productEntities = productRepository.findAllProductsByCategory(category);

            // Map the list of ProductEntity to ProductResponseDTO
            List<ProductResponseDTO> productResponseDTOS = productEntities.stream()
                    .map(productEntity -> modelMapper.map(productEntity, ProductResponseDTO.class))
                    .collect(Collectors.toList());

            return productResponseDTOS;
        } catch (Exception e) {
            log.error("Error in loadProductsByCategory: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ProductResponseDTO findProductsById(long productId) {
        try {
            System.out.println("findProductsById ID: " + productId);

            Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
            if (productEntityOptional.isPresent()) {
                ProductEntity productEntity = productEntityOptional.get();
                ProductResponseDTO productResponseDTO = new ProductResponseDTO();
                productResponseDTO = modelMapper.map(productEntity, ProductResponseDTO.class);
                // Map CategoryEntity to categoryResponseDTO
                CategoryEntity categoryEntity = productEntity.getCategoryEntity();
                productResponseDTO.setCategoryResponseDTO(
                        modelMapper.map(categoryEntity, CategoryResponseDTO.class)
                );
                return productResponseDTO;
            } else {
                throw new AbcRestaurantException(USER_NOT_FOUND,"Product Not Found");
            }

        } catch (AbcRestaurantException e) {
            log.error("Product not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error in findProductsById: {}", e.getMessage(), e);
            throw e;
        }
    }



    private String saveAttachment(ProductRequestDTO productRequestDTO) {
        try {
            String imageUrl = "";
            if (productRequestDTO.getImage() != null) {
                imageUrl = fileService.saveMultipartFile(productRequestDTO.getImage(), productRequestDTO.getImage().getContentType());
            }
            return imageUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

}
