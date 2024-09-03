package ABC_Restaurant.example.ABC_Restaurant.service.impl;

import ABC_Restaurant.example.ABC_Restaurant.dto.AdminDTO;
import ABC_Restaurant.example.ABC_Restaurant.entity.AdminEntity;
import ABC_Restaurant.example.ABC_Restaurant.repository.AdminRepository;
import ABC_Restaurant.example.ABC_Restaurant.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    public AdminServiceImpl(AdminRepository adminRepository, ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public AdminDTO getAdminDetails(String email) {
        try {

            Optional<AdminEntity> byEmail = adminRepository.findByEmail(email);
//            if (!byEmail.isPresent()) throw new ExecutionControl.UserException(USER_NOT_FOUND, "User not found for the given email");
            AdminDTO map = modelMapper.map(byEmail.get(), AdminDTO.class);
            map.setPassword(null);
            return map;
        } catch (Exception e) {
            throw e;
        }
    }
}
