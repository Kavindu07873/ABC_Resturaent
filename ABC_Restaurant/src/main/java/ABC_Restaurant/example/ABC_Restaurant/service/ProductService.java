package ABC_Restaurant.example.ABC_Restaurant.service;

import ABC_Restaurant.example.ABC_Restaurant.dto.Response.ProductResponseDTO;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<ProductResponseDTO> loadAllCustomer(int page, int size);
}
