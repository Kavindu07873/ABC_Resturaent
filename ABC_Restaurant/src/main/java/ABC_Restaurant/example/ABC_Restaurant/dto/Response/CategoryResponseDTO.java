package ABC_Restaurant.example.ABC_Restaurant.dto.Response;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryResponseDTO {
    private String  name;
    private String description;
    private String image;
}
