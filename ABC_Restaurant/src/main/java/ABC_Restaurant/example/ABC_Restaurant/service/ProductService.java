package ABC_Restaurant.example.ABC_Restaurant.service;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.ProductRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.ProductResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<ProductResponseDTO> loadAllProducts(int page, int size);

    void saveNewProduct(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> loadProductsByCategory(long category);

    ProductResponseDTO findProductsById(long productId);
}
