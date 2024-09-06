package ABC_Restaurant.example.ABC_Restaurant.service.impl;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.RegisterRequest;
import ABC_Restaurant.example.ABC_Restaurant.dto.UserDTO;
import ABC_Restaurant.example.ABC_Restaurant.entity.UserEntity;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserRole;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserStatus;
import ABC_Restaurant.example.ABC_Restaurant.exception.AbcRestaurantException;
import ABC_Restaurant.example.ABC_Restaurant.repository.UserRepository;
import ABC_Restaurant.example.ABC_Restaurant.service.UserService;
import ABC_Restaurant.example.ABC_Restaurant.utill.AccessTokenValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static ABC_Restaurant.example.ABC_Restaurant.constant.ApplicationConstant.USER_NOT_FOUND;
import static javax.crypto.Cipher.SECRET_KEY;

@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {



    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final AccessTokenValidator accessTokenValidator;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, @Lazy AccessTokenValidator accessTokenValidator) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenValidator = accessTokenValidator;
    }


    @Override
    public UserEntity getUserDetailsForLogin(String email) {
        log.info("Start function getUserDetailsForLogin @param email : {}", email);
        try {
            Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
            if (!userEntityOptional.isPresent())
                throw new AbcRestaurantException(USER_NOT_FOUND, "User not found for the given email");
            UserEntity userEntity = userEntityOptional.get();

//            UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
//            userDTO.setPassword(null);

            return userEntity;
        } catch (Exception e) {
            log.error("Function getUserDetailsForLogin : {}", e.getMessage(), e);
//            throw new ExecutionControl.UserException(COMMON_ERROR_CODE, e.getMessage());
        }
        return null;
    }

    @Override
    public void saveNewUser(RegisterRequest registerRequest) {
        try {
            log.info(registerRequest.getUsername());
            log.info(registerRequest.getEmail());
            log.info(registerRequest.getPassword());

            if (userRepository.existsByUsername(registerRequest.getUsername())) {
                throw new Exception("Username is already taken.");
            }
            if (userRepository.existsByEmail(registerRequest.getEmail())) {
                throw new Exception("Email is already in use.");
            }

            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(registerRequest.getUsername());
            userEntity.setEmail(registerRequest.getEmail());
            userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

            userEntity.setUserRole(UserRole.CUSTOMER);
            userEntity.setStatus(UserStatus.ACTIVE);

            userRepository.save(userEntity);

            System.out.println("hello World");
        } catch (Exception e) {
            log.error("Function saveUserAndGetAccessToken : {}", e.getMessage());
            try {
                throw e;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public String userLogin(RegisterRequest registerRequest) {
        try {
            log.info(registerRequest.getEmail());
            log.info(registerRequest.getPassword());

            UserEntity user = userRepository.findByEmail(registerRequest.getEmail())
                    .orElseThrow(() -> new Exception("User not found"));

            if (!passwordEncoder.matches(registerRequest.getPassword(), registerRequest.getPassword())) {
                throw new Exception("Invalid credentials");
            }
            // Generate JWT Token
            return generateToken(user);
        } catch (Exception e) {
//            log.error("Function saveUserAndGetAccessToken : {}", e.getMessage());
            try {
                throw e;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public UserEntity findProfile() {
        System.out.println("findProfile");
        try {
            return accessTokenValidator.retrieveUserInformationFromAuthentication();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    private String generateToken(UserEntity user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
                .signWith(SignatureAlgorithm.HS256, String.valueOf(SECRET_KEY))
                .compact();
    }
}
