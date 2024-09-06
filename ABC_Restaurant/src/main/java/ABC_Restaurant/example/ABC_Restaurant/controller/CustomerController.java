package ABC_Restaurant.example.ABC_Restaurant.controller;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.AddNewUserRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CustomerResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.common.CommonResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.service.CustomerService;
import ABC_Restaurant.example.ABC_Restaurant.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerNewUser(@RequestBody AddNewUserRequestDTO addNewUserRequestDTO) {
//        System.out.println("Test 1");
        customerService.saveNewCustomer(addNewUserRequestDTO);
        return new ResponseEntity<>(new CommonResponseDTO(true, "You can proceed normal sign up process"), HttpStatus.OK);
    }

    @GetMapping(value = "/find-all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAllCustomer(@RequestParam int page,
                                          @RequestParam int size) {
        Page<CustomerResponseDTO> customerResonseDTOPage = customerService.loadAllCustomer(page ,size);
        return new ResponseEntity<>(new CommonResponseDTO(true,customerResonseDTOPage ), HttpStatus.OK);
    }

}
