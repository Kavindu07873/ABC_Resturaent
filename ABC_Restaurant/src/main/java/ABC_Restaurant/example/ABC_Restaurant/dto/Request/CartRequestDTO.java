package ABC_Restaurant.example.ABC_Restaurant.dto.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartRequestDTO {
    private long productId;
    private int quantity;
}
