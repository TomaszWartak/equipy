package pl.javastart.equipy.assets;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Wyposażenie z podanym numerem seryjnym już istnieje")
public class DuplicateSerialNumberException extends RuntimeException { }
