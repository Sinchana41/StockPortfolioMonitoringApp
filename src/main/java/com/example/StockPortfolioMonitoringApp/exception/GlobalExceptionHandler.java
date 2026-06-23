package com.example.StockPortfolioMonitoringApp.exception;

import com.example.StockPortfolioMonitoringApp.utility.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice //@ControllerAdvice → Handles exceptions globally
                      // @ResponseBody → Returns data as JSON instead of a view.
public class GlobalExceptionHandler {



          @ExceptionHandler(UserAlreadyExistsException.class)
          public ResponseEntity<ErrorResponse> handleAlreadyExist(UserAlreadyExistsException ex){

              ErrorResponse response = new ErrorResponse(
                      HttpStatus.BAD_REQUEST.value(),
                      "Bad Request",
                      ex.getMessage(),
                      LocalDateTime.now()
              );

              return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

          }

          @ExceptionHandler(InvalidCredentialsException.class)
          public ResponseEntity<ErrorResponse> invalidCredentials(InvalidCredentialsException ex){

                ErrorResponse response = new ErrorResponse(
                      HttpStatus.UNAUTHORIZED.value(),
                      "invalid credentials",
                       ex.getMessage(),
                       LocalDateTime.now()
                );

            return  new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);

          }

         @ExceptionHandler(PortfolioAlreadyExistsException.class)
         public ResponseEntity<ErrorResponse> handleAlreadyExist(PortfolioAlreadyExistsException ex){

            ErrorResponse response = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                      "bad request",
                        ex.getMessage(),
                        LocalDateTime.now()
            );
          return  new ResponseEntity<>(response,HttpStatus.NO_CONTENT);

    }

          @ExceptionHandler(Exception.class)
          public ResponseEntity<ErrorResponse> handleGenericException(Exception ex){

              ErrorResponse response = new ErrorResponse(
                      HttpStatus.INTERNAL_SERVER_ERROR.value(),
                      "Internal server error",
                      ex.getMessage(),
                      LocalDateTime.now()
              );

              return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
          }
}
