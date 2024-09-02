//package ABC_Restaurant.example.ABC_Restaurant.service.impl;
//
//import ABC_Restaurant.example.ABC_Restaurant.service.Oauth2UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import static ABC_Restaurant.example.ABC_Restaurant.constant.OAuth2TokenConstant.CLIENT_ID;
//
//@Service(value = "userService")
//@Log4j2
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//public class Oauth2UserServiceImpl implements Oauth2UserService, UserDetailsService {
//
//    private final HttpServletRequest request;
////    @Autowired
////    private AccessTokenValidator accessTokenValidator;
//
//    public Oauth2UserServiceImpl(HttpServletRequest request) {
//        this.request = request;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
//            log.info("loadUserByUsername username: {}", username);
//
//            // gets current authentication principal
//            UsernamePasswordAuthenticationToken authentication =
//                    (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//            User user = (User) authentication.getPrincipal();
//            String clientId = user.getUsername();
//            if (clientId.equals(CLIENT_ID)) {
//
//                String ms_token = request.getParameter("password");
////                accessTokenValidator.validate(ms_token);
//
//
//
//            }
//
//            }catch (Exception e){
//            log.error("Function loadUserByUsername : {}", e.getMessage(), e);
//            throw e;
//        }
//
//
//
//
//        return null;
//    }
//}
