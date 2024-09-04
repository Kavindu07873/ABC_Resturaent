package ABC_Restaurant.example.ABC_Restaurant.dto.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommonResponseDTO {
    private boolean success;
    private String msg;
}
