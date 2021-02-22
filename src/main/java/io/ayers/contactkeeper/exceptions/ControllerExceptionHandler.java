package io.ayers.contactkeeper.exceptions;

import io.ayers.contactkeeper.models.response.ErrorMessageResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    // With more time, Each exception would be handled here
    // example:
    //  NotFoundExceptions
    //  DataIntegrityExceptions
    //  UnsupportedMediaType

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessageResponseModel> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(ErrorMessageResponseModel.builder()
                                                            .message(String.format("Error Occurred: %s",
                                                                    ex.toString()))
                                                            .timestamp(new Date())
                                                            .build());
    }
}
