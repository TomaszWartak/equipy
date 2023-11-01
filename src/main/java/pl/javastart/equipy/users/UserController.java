package pl.javastart.equipy.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        List<UserDto> list = new ArrayList<>();
        if (lastName==null) {
            list = userService.getAllUsers();
        } else {
            list = userService.getUsersByPartOfLastName( lastName );
        }
        return list;
    }

}
