package ABC_Restaurant.example.ABC_Restaurant.dto.Request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRequestDTO {
    private String  name;
    private String  category;
    private String  description;
    private String  price;
    private String  quantityInStock;
//    private String  unitPrice;
    private MultipartFile image;
}
