package Learning.springboot.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

@ExceptionHandler
    public String handlerException(Exception exception){
        return exception.getMessage();
    }


}
