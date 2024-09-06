package ABC_Restaurant.example.ABC_Restaurant.dto.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommonResponseDTO {
    private boolean success;
    private String msg;
    private Object body;

    public CommonResponseDTO(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public CommonResponseDTO(boolean success, Object body) {
        this.success = success;
        this.body = body;
    }

    public CommonResponseDTO(boolean success, String msg, Object body) {
        this.success = success;
        this.msg = msg;
        this.body = body;
    }
}
