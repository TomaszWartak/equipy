package pl.javastart.equipy.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@Controller
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
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        if (userDto.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Zapisywany obiekt nie może mieć ustawionego id"
            );
        }
        UserDto savedUser = userService.saveUser(userDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }
}
