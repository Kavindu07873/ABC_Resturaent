package ABC_Restaurant.example.ABC_Restaurant.service.impl;

import ABC_Restaurant.example.ABC_Restaurant.dto.Response.ProductResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.service.AdminService;
import ABC_Restaurant.example.ABC_Restaurant.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Page<ProductResponseDTO> loadAllCustomer(int page, int size) {
        return null;
    }
}
