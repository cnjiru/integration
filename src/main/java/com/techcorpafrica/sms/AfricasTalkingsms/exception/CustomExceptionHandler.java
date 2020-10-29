package com.techcorpafrica.sms.AfricasTalkingsms.exception;

import com.techcorpafrica.sms.africastalkingsms.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    )
    {
        // Status Message For the error captured
        Map<String,String> errorList = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->errorList.put(error.getField(),error.getDefaultMessage()));

        ResponseDTO responseString = new ResponseDTO("102",errorList.toString(),"-1");
        // response to send back to client
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }

    // handle all errors
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ResponseDTO responseString = new ResponseDTO(HttpStatus.BAD_REQUEST.toString(),ex.getLocalizedMessage(),
                "-1");
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ){
        ResponseDTO responseString = new ResponseDTO(HttpStatus.BAD_REQUEST.toString(),
                "Http RequestMethod NotSupported",
                "-1");
        return new ResponseEntity<>(responseString,HttpStatus.OK);
    }

    // handle malformed Json Request Payload
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        ResponseDTO responseString = new ResponseDTO(HttpStatus.BAD_REQUEST.toString(),
                "JsonMessage Malformed",
                "-1");
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }
}