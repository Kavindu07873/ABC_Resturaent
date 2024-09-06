package ABC_Restaurant.example.ABC_Restaurant.dto.Response;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponseDTO {
    private String  mealName;
    private String  mealType;
    private String  description;
}
