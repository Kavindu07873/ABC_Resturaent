//package ABC_Restaurant.example.ABC_Restaurant.utill;
//
//import com.auth0.jwk.Jwk;
//import com.auth0.jwk.JwkProvider;
//import com.auth0.jwk.UrlJwkProvider;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.ceyentra.lbclslaserver.entity.UserEntity;
//import com.ceyentra.lbclslaserver.entity.VendorEntity;
//import com.ceyentra.lbclslaserver.exception.LBCLServiceException;
//import com.ceyentra.lbclslaserver.service.UserService;
//import com.ceyentra.lbclslaserver.service.VendorService;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Header;
//import io.jsonwebtoken.Jwt;
//import io.jsonwebtoken.Jwts;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.net.URL;
//import java.security.interfaces.RSAPublicKey;
//import java.util.Date;
//
//import static com.ceyentra.lbclslaserver.constant.ApplicationConstant.*;
//
//@Slf4j
//@Component
//public class AccessTokenValidator {
//
//    @Autowired
//    private UserService userService;
////    private final VendorService vendorService;
//
//    @Autowired
//    private VendorService vendorService;
//
//    @Value("${staff-tenant-id}")
//    private String tenantId;
//
//    @Value("${staff-app-id}")
//    private String appId;
//
//    public AccessTokenValidator() {
////        this.userService = userService;
////        this.vendorService = vendorService;
//    }
//
//    public boolean validate(String token) {
//        try {
//            log.info("Execute method validate");
//
//            DecodedJWT jwt = JWT.decode(token);
//
//
//            JwkProvider provider = null;
//            Jwk jwk = null;
//            Algorithm algorithm = null;
//
//
//            provider = new UrlJwkProvider(new URL("https://login.microsoftonline.com/" + tenantId + "/discovery/v2.0/keys"));
//            jwk = provider.get(jwt.getKeyId());
//            algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
//            algorithm.verify(jwt);// if the token signature is invalid, the method will throw SignatureVerificationException
//        } catch (Exception e) {
//
//            log.error("failed: " + e.getMessage());
//            return false;
//
//        }
//
//        return true;
//    }
//
//    public boolean checkIntended(String token) {
//        try {
//            log.info("Execute method checkIntended");
//
//            int i = token.lastIndexOf('.');
//            String withoutSignature = token.substring(0, i + 1);
//            Jwt<Header, Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);
//
//            String aud = untrusted.getBody().get("aud", String.class);
//            Date expDate = untrusted.getBody().get("exp", Date.class);
//
//            if (!aud.equalsIgnoreCase(appId)) return false;
//            if (expDate.before(new Date())) return false;
//
//        } catch (Exception e) {
//            log.info(e.getMessage());
//            return false;
//        }
//        return true;
//    }
//
//    public Jwt<Header, Claims> getClaims(String token) {
//        try {
//            log.info("Execute method getClaims");
//
//            int i = token.lastIndexOf('.');
//            String withoutSignature = token.substring(0, i + 1);
//            Jwt<Header, Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);
//            return untrusted;
//        } catch (Exception e) {
//            log.info(e.getMessage());
//        }
//        return null;
//    }
//
//    public UserEntity retrieveUserInformationFromAuthentication() {
//        log.info("Execute method retrieveUserInformationFromAuthentication");
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (!(authentication instanceof AnonymousAuthenticationToken)) {
//                UserEntity user = userService.getUserByEmail(authentication.getName());
//
//                if (user == null) {
//                    throw new LBCLServiceException(USER_NOT_FOUND, "this user is not registered yet");
//                }
//                return user;
//            }
//            throw new LBCLServiceException(RESOURCE_NOT_FOUND, "Can't find user details from token");
//        } catch (Exception e) {
//            log.error("Method retrieveB2BUserInformationFromAuthentication : " + e.getMessage());
//            throw new LBCLServiceException(OPERATION_FAILED, e.getMessage());
//        }
//    }
//
//
//    public VendorEntity retrieveVendorInformationFromAuthentication(){
//        log.info("Execute method retrieveVendorInformationFromAuthentication");
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            log.info("authentication : {}", authentication);
//            log.info("authentication.name : {}", authentication.getName());
//
//            if (!(authentication instanceof AnonymousAuthenticationToken)) {
////                VendorEntity vendor = vendorService.getVendorByNumber(authentication.getName());
//                log.info("authentication.getName() : {}", authentication.getName());
//
//                VendorEntity vendor = vendorService.getVendorById(authentication.getName());
//                if (vendor == null) {
//                    throw new LBCLServiceException(VENDOR_NOT_FOUND, "this vendor is not registered yet");
//                }
//                log.info("vendor : {}", vendor.getId());
//
//                return vendor;
//            }
//            throw new LBCLServiceException(RESOURCE_NOT_FOUND, "Can't find vendor details from token");
//        } catch (Exception e) {
//            log.error("Method retrieveB2BVendorInformationFromAuthentication : {}", e.getMessage());
//            throw new LBCLServiceException(OPERATION_FAILED, e.getMessage());
//        }
//    }
//
//
//    public boolean isAuthenticated() {
//        log.info("Execute method isAuthenticated");
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            log.info("isAuthenticated " + authentication.isAuthenticated());
//            if (!(authentication instanceof AnonymousAuthenticationToken)) {
//                log.info("isAuthenticated : instance of AnonymousAuthenticationToken");
//                log.info("isAuthenticated : email : " + authentication.getName());
//                UserEntity user = userService.getUserByEmail(authentication.getName());
//                log.info("isAuthenticated : user : " + (user!=null));
//                return user != null;
//            }
//            return false;
//        } catch (Exception e) {
//            log.error("Method isAuthenticated : " + e.getMessage());
//            throw new LBCLServiceException(OPERATION_FAILED, e.getMessage());
//        }
//    }
//
//}
