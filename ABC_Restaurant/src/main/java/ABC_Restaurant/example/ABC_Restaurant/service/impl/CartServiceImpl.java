package ABC_Restaurant.example.ABC_Restaurant.service.impl;

import ABC_Restaurant.example.ABC_Restaurant.dto.Request.CartRequestDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CartResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.CategoryResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.dto.Response.ProductResponseDTO;
import ABC_Restaurant.example.ABC_Restaurant.entity.CartEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.CategoryEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.ProductEntity;
import ABC_Restaurant.example.ABC_Restaurant.entity.UserEntity;
import ABC_Restaurant.example.ABC_Restaurant.repository.CartRepository;
import ABC_Restaurant.example.ABC_Restaurant.repository.CategoryRepository;
import ABC_Restaurant.example.ABC_Restaurant.repository.ProductRepository;
import ABC_Restaurant.example.ABC_Restaurant.service.CartService;
import ABC_Restaurant.example.ABC_Restaurant.service.CategoryService;
import ABC_Restaurant.example.ABC_Restaurant.utill.AccessTokenValidator;
import ABC_Restaurant.example.ABC_Restaurant.utill.FileService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CartServiceImpl implements CartService {
    @Autowired
    private FileService fileService;
    @Autowired
    private ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final AccessTokenValidator accessTokenValidator;
    private final CartRepository cartRepository;


    public CartServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, AccessTokenValidator accessTokenValidator, CartRepository cartRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.accessTokenValidator = accessTokenValidator;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addNewCart(CartRequestDTO cartRequestDTO) {
        try {
            Optional<ProductEntity> optionalProductEntity = productRepository.findById(cartRequestDTO.getProductId());
            if (!optionalProductEntity.isPresent())
                throw new Exception("Product Not Found.");
            ProductEntity productEntity = optionalProductEntity.get();
            CartEntity cartEntity = new CartEntity();
            cartEntity.setProductEntity(productEntity);
            UserEntity userEntity = accessTokenValidator.retrieveUserInformationFromAuthentication();
            cartEntity.setUserEntity(userEntity);
            cartEntity.setQuantity(cartRequestDTO.getQuantity());
            if(cartRequestDTO.getQuantity() != 0){
                cartEntity.setTotalPrice(productEntity.getPrice() * cartRequestDTO.getQuantity());
            }
            cartRepository.save(cartEntity);

        } catch (Exception e) {
            log.error("Function saveNewProduct : {}", e.getMessage());
            try {
                throw e;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public List<CartResponseDTO> findCartByUser() {
        try {
            UserEntity userEntity = accessTokenValidator.retrieveUserInformationFromAuthentication();

            List<CartEntity> cartEntityList = cartRepository.findByUserId(userEntity.getId());

            List<CartResponseDTO> cartResponseDTOList = new ArrayList<>();

            for (CartEntity cartEntity : cartEntityList) {
//            CartResponseDTO cartResponseDTO = modelMapper.map(cartEntity, CartResponseDTO.class);
                CartResponseDTO cartResponseDTO = new CartResponseDTO();
                cartResponseDTO.setId(cartEntity.getId());
                cartResponseDTO.setProductId(cartEntity.getProductEntity().getId());
                ProductResponseDTO productResponseDTO =modelMapper.map(cartEntity.getProductEntity() ,ProductResponseDTO.class);
                cartResponseDTO.setProduct(productResponseDTO);
                cartResponseDTO.setQuantity(cartEntity.getQuantity());
                cartResponseDTO.setTotalPrice(cartEntity.getTotalPrice());
                cartResponseDTOList.add(cartResponseDTO);
            }
            return cartResponseDTOList;
        } catch (Exception e) {
            log.error("Function findCartByUser : {}", e.getMessage());
            throw e;
        }
    }
}
