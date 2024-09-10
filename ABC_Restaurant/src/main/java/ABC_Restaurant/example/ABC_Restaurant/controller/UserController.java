package ABC_Restaurant.example.ABC_Restaurant.controller;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.AddNewUserRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Request.RegisterRequest;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CustomerResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.UserResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.common.CommonResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.entity.UserEntity;
import ABC_Restaurant.example.ABC_Restaurant.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity registerNewUser(@RequestBody RegisterRequest registerRequest) {
        System.out.println("Test 1");
        userService.saveNewUser(registerRequest);
        return new ResponseEntity<>(new CommonResponseDTO(true, "Customer Succefully Register"), HttpStatus.OK);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signIn(@RequestBody RegisterRequest registerRequest) {
//        System.out.println("Test 2");

//        try {
            String token = userService.userLogin(registerRequest);

//            return ResponseEntity.ok().body("Login Successful. Token: " + token);
            return new ResponseEntity<>(new CommonResponseDTO(true, "user Successfully login",token), HttpStatus.OK);

//        } catch (Exception e) {
////            return ResponseEntity.badRequest().body(e.getMessage());
//            return new ResponseEntity<>(new CommonResponseDTO(true, e.getMessage()), HttpStatus.BAD_REQUEST);
//
//        }
    }


    @GetMapping(value = "/my-profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAllUser() {
        System.out.println("my profile");
        UserResponseDTO userResponseDTO = userService.findProfile();
        return new ResponseEntity<>(new CommonResponseDTO(true,userResponseDTO ), HttpStatus.OK);
    }
}
