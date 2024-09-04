package ABC_Restaurant.example.ABC_Restaurant.controller;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.AddNewUserRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.common.CommonResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerNewUser(@ModelAttribute AddNewUserRequestDTO addNewUserRequestDTO) {
        System.out.println("Test 1");
        userService.saveNewUser(addNewUserRequestDTO);
        return new ResponseEntity<>(new CommonResponseDTO(true, "You can proceed normal sign up process"), HttpStatus.OK);
    }

    @GetMapping(value = "/sign", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signIn() {
        System.out.println("Test 1");
        log.info("Test 1");
//        userService.saveNewUser(addNewUserRequestDTO);
        return new ResponseEntity<>(new CommonResponseDTO(true, "You can proceed normal sign up process"), HttpStatus.OK);
    }

}
