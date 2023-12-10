package pl.javastart.equipy.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateTimeProvider {

    public LocalDateTime currentLocalDateTime() {
        return LocalDateTime.now();
    }
}
