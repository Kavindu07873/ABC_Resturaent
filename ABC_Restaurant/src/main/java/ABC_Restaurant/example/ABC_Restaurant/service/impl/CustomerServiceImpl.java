package ABC_Restaurant.example.ABC_Restaurant.service.impl;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.AddNewUserRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.entity.CustomerEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.UserEntity;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserRole;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserStatus;
import ABC_Restaurant.example.ABC_Restaurant.exception.AbcRestaurantException;
import ABC_Restaurant.example.ABC_Restaurant.repository.CustomerRepository;
import ABC_Restaurant.example.ABC_Restaurant.repository.UserRepository;
import ABC_Restaurant.example.ABC_Restaurant.service.CustomerService;
import ABC_Restaurant.example.ABC_Restaurant.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
            log.info(addNewUserRequestDTO.getFullName());
            log.info(addNewUserRequestDTO.getEmail());
            log.info(addNewUserRequestDTO.getPassword());

            Optional<UserEntity> optionalUserEntity = customerRepository.findByName(addNewUserRequestDTO.getEmail());
            if (optionalUserEntity.isPresent())
                throw new AbcRestaurantException(USER_NOT_FOUND, "User Already Available");

            CustomerEntity customerEntity = new CustomerEntity();

            customerEntity.setName(addNewUserRequestDTO.getFullName());
            customerEntity.setEmail(addNewUserRequestDTO.getEmail());
            customerEntity.setPassword(addNewUserRequestDTO.getPassword());
            customerEntity.setUserRole(UserRole.CUSTOMER);
            customerEntity.setStatus(UserStatus.ACTIVE);

            customerRepository.save(customerEntity);

            System.out.println("hello World");
        } catch (Exception e) {
            log.error("Function saveUserAndGetAccessToken : {}", e.getMessage());
            throw e;
        }
    }
}
