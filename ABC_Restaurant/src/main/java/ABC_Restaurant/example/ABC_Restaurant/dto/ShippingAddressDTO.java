package ABC_Restaurant.example.ABC_Restaurant.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShippingAddressDTO {
    private String  postalCode;
    private String phone;
    private String  detailedAddress;
    private String city;
}
