package autoTrade.api;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiControllerAdvice {


    @ExceptionHandler(BindException.class)
    public ApiResponse<Object> bindException(BindException e) {
        ObjectError objectError = e.getAllErrors().get(0);

        return ApiResponse.of(HttpStatus.BAD_REQUEST, objectError.getDefaultMessage());
    }



}
