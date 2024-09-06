package ABC_Restaurant.example.ABC_Restaurant.dto.Request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryRequestDTO {
    private String  name;
    private String description;
    private MultipartFile image;
}
