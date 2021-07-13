package br.com.bootcamp.apidiplomas.exception.handler;

import br.com.bootcamp.apidiplomas.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationException(MethodArgumentNotValidException e) {
        List<String> fieldErrorsString = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> f.getField()+": "+f.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(),  String.join(", ", fieldErrorsString)));
    }
}
