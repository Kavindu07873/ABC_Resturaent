package ABC_Restaurant.example.ABC_Restaurant.config;

import ABC_Restaurant.example.ABC_Restaurant.constant.OAuth2TokenConstant;
import ABC_Restaurant.example.ABC_Restaurant.dto.AdminDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.UserDTO;
import ABC_Restaurant.example.ABC_Restaurant.service.AdminService;
import ABC_Restaurant.example.ABC_Restaurant.service.Oauth2UserService;
import ABC_Restaurant.example.ABC_Restaurant.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomTokenEnhance extends JwtAccessTokenConverter {
    private final Oauth2UserService oauth2UserService;
    private final HttpServletRequest request;
    private final BCryptPasswordEncoder encoder;
    private final AdminService adminService;
    private final UserService userService;

    @Autowired
    public CustomTokenEnhance(Oauth2UserService oauth2UserService, HttpServletRequest request, BCryptPasswordEncoder encoder, AdminService adminService, UserService userService) {
        this.oauth2UserService = oauth2UserService;
        this.request = request;
        this.encoder = encoder;
        this.adminService = adminService;
        this.userService = userService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

//        LOGGER.info("Start: CustomTokenEnhancer enhance");

        final Map<String, Object> additionalInfo = new HashMap<>();

        User user = (User) oAuth2Authentication.getPrincipal();
//        LOGGER.info("Execute: CustomTokenEnhancer enhance user name : " + user.getUsername());

        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User account = (User) authentication.getPrincipal();

        if (account.getUsername().equals(OAuth2TokenConstant.ADMIN_CLIENT_ID)) {

            AdminDTO adminDetails = adminService.getAdminDetails(user.getUsername());
            additionalInfo.put("admin", adminDetails);


        } else if (account.getUsername().equals(OAuth2TokenConstant.STAFF_CLIENT_ID)){

            UserDTO userDetails = userService.getUserDetailsForLogin(user.getUsername());
            additionalInfo.put("user", userDetails);

        }
//        else if (account.getUsername().equals(OAuth2TokenConstant.CUSTOMER_CLIENT_ID)){
//            UserDTO userDTO = userService.getEtenderUserDetails(user.getUsername());
//            additionalInfo.put("user", userDTO);
//        }

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        // set custom claims
        return super.enhance(oAuth2AccessToken, oAuth2Authentication);
    }

}
