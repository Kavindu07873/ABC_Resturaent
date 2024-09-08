package ABC_Restaurant.example.ABC_Restaurant.dto.Response;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartResponseDTO {
    private long id;
    private int quantity;
    private double totalPrice;
    private long productId;
    private ProductResponseDTO product;

}
