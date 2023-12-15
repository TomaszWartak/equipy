package pl.javastart.equipy.assignments;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Brak przyporządkowania o takim ID")
public class AssignmentNotFoundException extends RuntimeException {}