package ABC_Restaurant.example.ABC_Restaurant.service.impl;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.AddNewUserRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CustomerResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.entity.CustomerEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.UserEntity;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserRole;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserStatus;
import ABC_Restaurant.example.ABC_Restaurant.exception.AbcRestaurantException;
import ABC_Restaurant.example.ABC_Restaurant.repository.CustomerRepository;
import ABC_Restaurant.example.ABC_Restaurant.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ABC_Restaurant.example.ABC_Restaurant.constant.ApplicationConstant.INVALID_ENTERED;
import static ABC_Restaurant.example.ABC_Restaurant.constant.ApplicationConstant.USER_NOT_FOUND;

@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CustomerServiceImpl  implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl( CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveNewCustomer(AddNewUserRequestDTO addNewUserRequestDTO) {
        try {
            System.out.println(addNewUserRequestDTO.getFullName());
//            System.out.println(addNewUserRequestDTO.getEmail());
            System.out.println(addNewUserRequestDTO.getPassword());

            Optional<UserEntity> optionalUserEntity = customerRepository.findByName(addNewUserRequestDTO.getName());
            if (optionalUserEntity.isPresent())
                throw new AbcRestaurantException(USER_NOT_FOUND, "User Already Available");

            CustomerEntity customerEntity = new CustomerEntity();

            customerEntity.setName(addNewUserRequestDTO.getName());
            customerEntity.setAddress(addNewUserRequestDTO.getAddress());
            customerEntity.setSalary(addNewUserRequestDTO.getSalary());
            customerEntity.setUserRole(UserRole.CUSTOMER);
            customerEntity.setStatus(UserStatus.ACTIVE);

            customerRepository.save(customerEntity);

            System.out.println("hello World");
        } catch (Exception e) {
            log.error("Function saveUserAndGetAccessToken : {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<CustomerResponseDTO> loadAllCustomer(int page, int size) {
        try {

            if (page < 0 || size < 0) {
                throw new AbcRestaurantException(INVALID_ENTERED, "The page is not in a valid format.");
            }
            Pageable pageable = PageRequest.of(page, size);

            Page<CustomerEntity> customerEntities = customerRepository.findAll(pageable );


            List<CustomerResponseDTO> customerResponseDTOS = customerEntities
                    .getContent()
                    .stream()
                    .map(this::getProcessResponseDTO)
                    .collect(Collectors.toList());

            return new PageImpl<>(customerResponseDTOS, pageable, customerEntities.getTotalElements());

        } catch (Exception e) {
//            log.error("Function saveUserAndGetAccessToken : {}", e.getMessage());
            throw e;
        }
    }

    private CustomerResponseDTO getProcessResponseDTO(CustomerEntity customerEntity) {
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setEmail(customerEntity.getAddress());
        customerResponseDTO.setFullName(customerEntity.getName());
        customerResponseDTO.setFullName(customerEntity.getName());
        return  customerResponseDTO;
    }
}
