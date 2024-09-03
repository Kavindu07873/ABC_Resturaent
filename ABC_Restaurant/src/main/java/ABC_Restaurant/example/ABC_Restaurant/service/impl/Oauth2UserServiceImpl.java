package ABC_Restaurant.example.ABC_Restaurant.service.impl;

import ABC_Restaurant.example.ABC_Restaurant.entity.AdminEntity;
import ABC_Restaurant.example.ABC_Restaurant.enums.UserStatus;
import ABC_Restaurant.example.ABC_Restaurant.exception.CustomOauthException;
import ABC_Restaurant.example.ABC_Restaurant.repository.AdminRepository;
import ABC_Restaurant.example.ABC_Restaurant.repository.UserRepository;
import ABC_Restaurant.example.ABC_Restaurant.service.Oauth2UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

import static ABC_Restaurant.example.ABC_Restaurant.constant.OAuth2TokenConstant.ADMIN_CLIENT_ID;
import static org.springframework.security.oauth2.common.util.OAuth2Utils.CLIENT_ID;

@Service(value = "userService")
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class Oauth2UserServiceImpl implements Oauth2UserService, UserDetailsService {

    private final HttpServletRequest request;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;


    public Oauth2UserServiceImpl(HttpServletRequest request, AdminRepository adminRepository, UserRepository userRepository) {
        this.request = request;
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            log.info("loadUserByUsername username: {}", username);

            // gets current authentication principal
            UsernamePasswordAuthenticationToken authentication =
                    (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            String clientId = user.getUsername();
            if (clientId.equals(ADMIN_CLIENT_ID)) {

                //admin
                Optional<AdminEntity> byEmail = adminRepository.findByEmail(username);
                if (!byEmail.isPresent()) throw new CustomOauthException("Invalid Credentials");
                AdminEntity adminEntity = byEmail.get();

                if (byEmail.get().getStatus().equals(UserStatus.DELETED) || byEmail.get().getStatus().equals(UserStatus.INACTIVE))
                    throw new CustomOauthException("Invalid Credentials");

                return new org.springframework.security.core.userdetails.User(adminEntity.getEmail(), adminEntity.getPassword(),
                        getAuthority(adminEntity.getUserRole().toString()));


            }
        } catch (Exception e) {
            log.error("Function loadUserByUsername : {}", e.getMessage(), e);
            throw e;
        }
        return null;
    }
    private List<SimpleGrantedAuthority> getAuthority(String roleName) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roleName));
    }
}
