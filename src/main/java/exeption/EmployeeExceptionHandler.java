package exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleSQLException(SQLException sqlException) {
        // Возвращает статус 500 (Internal Server Error)
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
