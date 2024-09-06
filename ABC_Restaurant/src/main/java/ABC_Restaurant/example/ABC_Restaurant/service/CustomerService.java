package ABC_Restaurant.example.ABC_Restaurant.service;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.AddNewUserRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CustomerResponseDTO;
import org.springframework.data.domain.Page;

public interface CustomerService {
    void saveNewCustomer(AddNewUserRequestDTO addNewUserRequestDTO);

    Page<CustomerResponseDTO> loadAllCustomer(int page, int size);
}
