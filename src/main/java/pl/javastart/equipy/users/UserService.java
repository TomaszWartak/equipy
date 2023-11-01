package pl.javastart.equipy.users;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ArrayList<UserDto> getAllUsers() {
        return (ArrayList<UserDto>) userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    public ArrayList<UserDto> getUsersByPartOfLastName( String lastName) {
        return (ArrayList<UserDto>) userRepository.findUserByLastNameContainingIgnoreCase( lastName )
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }
}
