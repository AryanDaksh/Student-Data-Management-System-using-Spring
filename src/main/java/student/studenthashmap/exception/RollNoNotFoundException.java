package student.studenthashmap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Roll Number is present.")
public class RollNoNotFoundException extends RuntimeException {

    public RollNoNotFoundException(String message) {
        super(message);
      }
}
