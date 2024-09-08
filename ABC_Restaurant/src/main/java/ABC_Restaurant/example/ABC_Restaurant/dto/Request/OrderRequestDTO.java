package ABC_Restaurant.example.ABC_Restaurant.dto.Request;

import ABC_Restaurant.example.ABC_Restaurant.dto.ShippingAddressDTO;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequestDTO {

    private String  postalCode;
    private String phone;
    private String detailedAddress;
    private String city;
    private ShippingAddressDTO shippingAddress;
}
