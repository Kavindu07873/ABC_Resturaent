package ABC_Restaurant.example.ABC_Restaurant.service.impl;

import ABC_Restaurant.example.ABC_Restaurant.dto.UserDTO;
import ABC_Restaurant.example.ABC_Restaurant.entity.UserEntity;
import ABC_Restaurant.example.ABC_Restaurant.repository.UserRepository;
import ABC_Restaurant.example.ABC_Restaurant.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static ABC_Restaurant.example.ABC_Restaurant.constant.ApplicationConstant.COMMON_ERROR_CODE;
import static ABC_Restaurant.example.ABC_Restaurant.constant.ApplicationConstant.USER_NOT_FOUND;

@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {
    ;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO getUserDetailsForLogin(String email) {
        log.info("Start function getUserDetailsForLogin @param email : {}", email);
        try {
            Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
//            if (!userEntityOptional.isPresent())
//                throw new ExecutionControl.UserException(USER_NOT_FOUND, "User not found for the given email");
            UserEntity userEntity = userEntityOptional.get();

            UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
            userDTO.setPassword(null);

            return userDTO;
        } catch (Exception e) {
            log.error("Function getUserDetailsForLogin : {}", e.getMessage(), e);
//            throw new ExecutionControl.UserException(COMMON_ERROR_CODE, e.getMessage());
        }
        return null;
    }
}
