package ABC_Restaurant.example.ABC_Restaurant.service.impl;

import ABC_Restaurant.example.ABC_Restaurant.entity.AdminEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.UserEntity;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserStatus;
import ABC_Restaurant.example.ABC_Restaurant.exception.CustomOauthException;
import ABC_Restaurant.example.ABC_Restaurant.repository.AdminRepository;
import ABC_Restaurant.example.ABC_Restaurant.repository.StaffRepository;
import ABC_Restaurant.example.ABC_Restaurant.repository.UserRepository;
import ABC_Restaurant.example.ABC_Restaurant.service.Oauth2UserService;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class Oauth2UserServiceImpl implements Oauth2UserService, UserDetailsService {

//    private final HttpServletRequest request;
//    private final AdminRepository adminRepository;
//    private final UserRepository userRepository;

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;

    public Oauth2UserServiceImpl(AdminRepository adminRepository, UserRepository userRepository, StaffRepository staffRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<AdminEntity> admin = adminRepository.findByEmail(username);

            if (admin.isPresent() && admin.get().getStatus().equals(UserStatus.ACTIVE.ACTIVE)) {
                return new org.springframework.security.core.userdetails.User(
                        admin.get().getEmail(), admin.get().getPassword(),
                        getAuthority(admin.get().getUserRole().name())
                );
            }

            Optional<UserEntity> customer = userRepository.findByEmail(username);

            if (customer.isPresent() && customer.get().getStatus().equals(UserStatus.ACTIVE)) {
                return new org.springframework.security.core.userdetails.User(
                        customer.get().getEmail(), customer.get().getPassword(),
                        getAuthority(customer.get().getUserRole().name()));
            }

//            Optional<S> staff = staffRepository.findByEmail(username);
//
//            if (staff.isPresent() && staff.get().getStatus().equals(CommonStatus.ACTIVE)) {
//                return new org.springframework.security.core.userdetails.User(
//                        staff.get().getEmail(), staff.get().getPassword(),
//                        getAuthority(staff.get().getUserRole().name()));
//            }

            throw new CustomOauthException("Invalid Credentials.");

        } catch (Exception e) {
            throw e;
        }
    }

    private List<SimpleGrantedAuthority> getAuthority(String roleName) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roleName));
    }
}
