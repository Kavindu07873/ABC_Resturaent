package ABC_Restaurant.example.ABC_Restaurant.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AbcRestaurantException extends RuntimeException{
    private int status;
    private String message;

    public AbcRestaurantException(int status,String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
