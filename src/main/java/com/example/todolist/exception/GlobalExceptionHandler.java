package com.example.todolist.exception;

import com.example.todolist.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Xử lý ngoại lệ ResourceNotFoundException và trả về phản hồi HTTP 404.
     * @param exception Ngoại lệ ResourceNotFoundException
     * @param webRequest WebRequest của yêu cầu
     * @return ResponseEntity chứa thông tin chi tiết về lỗi và mã trạng thái HTTP 404
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Xử lý ngoại lệ APIException và trả về phản hồi HTTP 400.
     * @param exception Ngoại lệ APIException
     * @param webRequest WebRequest của yêu cầu
     * @return ResponseEntity chứa thông tin chi tiết về lỗi và mã trạng thái HTTP 400
     */
    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorDetails> handleAPIException(APIException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Xử lý ngoại lệ Exception chung và trả về phản hồi HTTP 400.
     * @param exception   Ngoại lệ Exception chung
     * @param webRequest  WebRequest của yêu cầu
     * @return ResponseEntity chứa thông tin chi tiết về lỗi và mã trạng thái HTTP 400
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalExceptionHandler(Exception exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
