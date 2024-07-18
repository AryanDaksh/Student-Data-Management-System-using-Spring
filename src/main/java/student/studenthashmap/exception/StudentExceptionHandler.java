package student.studenthashmap.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class StudentExceptionHandler{
   
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(
		MethodArgumentNotValidException ex) {
		  Map<String, String> errors = new HashMap<>();
		  ex.getBindingResult().getAllErrors().forEach((error) -> {
			  String fieldName = ((FieldError) error).getField();
			  String errorMessage = error.getDefaultMessage();
			  errors.put(fieldName, errorMessage);
		  });
		  return new ResponseEntity<> (errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgException(IllegalArgumentException ey) {
	
		HttpStatus notFound = HttpStatus.NOT_FOUND;
	
		ApiException apiException = new ApiException(ey.getMessage(), ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))   
		);
		return new ResponseEntity<>(apiException, notFound);
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<Object> handleIllegalArgException(DateTimeParseException ex) {
	
		HttpStatus notFound = HttpStatus.NOT_FOUND;
	
		ApiException apiException = new ApiException(ex.getMessage(), ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))   
		);
		return new ResponseEntity<>(apiException, notFound);
	}
}
