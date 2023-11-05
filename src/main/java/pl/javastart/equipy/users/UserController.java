package pl.javastart.equipy.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<UserDto> getUsers(@RequestParam(required = false) String lastName) {
        List<UserDto> list;
        if (lastName==null) {
            list = userService.getAllUsers();
        } else {
            list = userService.getUsersByPartOfLastName( lastName );
        }
        return list;
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        if (userDto.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Inny użytkownik z takim peselem już istnieje"
            );
        }
        UserDto addedUser = userService.addUser(userDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(addedUser);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDto> getUser( @PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        if (userDto.getId()!=id) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu"
            );
        }
        return ResponseEntity.ok(userService.updateUser(userDto));
    }


}
