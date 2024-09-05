package ABC_Restaurant.example.ABC_Restaurant.exception;

import ABC_Restaurant.example.ABC_Restaurant.dto.common.ErrorMessageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static ABC_Restaurant.example.ABC_Restaurant.constant.ApplicationConstant.COMMON_ERROR_CODE;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleAnyException(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorMessageResponseDTO(false, 100, ""), HttpStatus.OK);
    }

    @ExceptionHandler(value = {AbcRestaurantException.class})
    public ResponseEntity handleABCException(AbcRestaurantException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorMessageResponseDTO(false, ex.getStatus(), ex.getMessage()), HttpStatus.OK);
    }
}
