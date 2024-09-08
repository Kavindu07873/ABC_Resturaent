package ABC_Restaurant.example.ABC_Restaurant.dto.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponseDTO {

    private long id;
    private String name;
    private String  mealType;
    private String  description;
    private String image;
    private double price;
    private int quantityInStock;
    private CategoryResponseDTO categoryResponseDTO;

}
