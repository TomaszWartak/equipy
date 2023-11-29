package pl.javastart.equipy.assets;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Brak wyposążenia o takim ID")
public class AssetNotFoundException extends RuntimeException {}