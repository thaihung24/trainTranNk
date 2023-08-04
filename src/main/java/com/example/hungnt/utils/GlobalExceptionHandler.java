package com.example.hungnt.utils;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        // Xử lý lỗi validation và trả về thông báo lỗi cụ thể
        List<String> errorMessages = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            errorMessages.add(error.getField() + ": " + error.getDefaultMessage());
        }
        Response res = new Response();
        res.setStatus("400");
        res.setMessage("Lỗi validation"+errorMessages);
        return res;
    }
}
