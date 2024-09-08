package ABC_Restaurant.example.ABC_Restaurant.dto.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryResponseDTO {
    private long id;
    private String  name;
    private String description;
    private String image;
}
