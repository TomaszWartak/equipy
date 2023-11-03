package pl.javastart.equipy.users;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.exceptions.DuplicatePeselException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ArrayList<UserDto> getAllUsers() {
        return (ArrayList<UserDto>) userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    public ArrayList<UserDto> getUsersByPartOfLastName( String lastName) {
        return (ArrayList<UserDto>) userRepository.findUserByLastNameContainingIgnoreCase( lastName )
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto saveUser( UserDto userDto ) {
        Optional<User> userByPesel = userRepository.findByPesel(userDto.getPesel());
        userByPesel.ifPresent(u -> {
            throw new DuplicatePeselException();
        });
        User user = UserMapper.toUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.toUserDto(savedUser);
    }
}
