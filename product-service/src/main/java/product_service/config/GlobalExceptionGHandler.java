package product_service.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import product_service.config.exception.NotFoundException;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionGHandler {

    @ExceptionHandler({NotFoundException.class})
    public ProblemDetail handleNotFoundException(NotFoundException ex, WebRequest request) {
      log.warn("Recurso no encontrado - Path: {}, Message: {}", request.getDescription(false), ex.getMessage());
      ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
      problemDetail.setTitle("Recurso No Encontrado");
      problemDetail.setType(URI.create("https://api.ecommerce.com/errors/not-found"));
      problemDetail.setProperty("timestamp", Instant.now());
      return problemDetail;
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        log.warn("Error de validación - Path: {}, Message: {}", request.getDescription(false), ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "La validación falló en uno o mas campos.");
        problemDetail.setTitle("Error de Validación");
        problemDetail.setType(URI.create("https://api.ecommerce.com/errors/validation-error"));
        problemDetail.setProperty("timestamp", Instant.now());
        Map<String,String> errorsField =  new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> errorsField.put(err.getField(), err.getDefaultMessage()));
        problemDetail.setProperty("errors", errorsField);
        return problemDetail;
    }


    @ExceptionHandler({Exception.class})
    public ProblemDetail handleGlobalException(Exception ex, WebRequest request) {
        log.error("Error inesperado - Path: {}, Message: {}", request.getDescription(false), ex.getMessage(), ex);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado. Por favor, intente nuevamente más tarde.");
        problemDetail.setTitle("Error Interno del Servidor");
        problemDetail.setType(URI.create("https://api.ecommerce.com/errors/internal-server-error"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }


}
