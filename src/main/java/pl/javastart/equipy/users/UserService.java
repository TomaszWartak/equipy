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

    public Optional<UserDto> getUserById( Long id) {
        return userRepository.findById( id ).map( UserMapper::toUserDto);
    }

    public ArrayList<UserDto> getUsersByPartOfLastName( String lastName) {
        return (ArrayList<UserDto>) userRepository.findUserByLastNameContainingIgnoreCase( lastName )
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto addUser(UserDto userDtoToAdd ) {
        Optional<User> userByPesel = userRepository.findByPesel(userDtoToAdd.getPesel());
        userByPesel.ifPresent(user -> {
            throw new DuplicatePeselException();
        });
        User user = UserMapper.toUser(userDtoToAdd);
        User addUser = userRepository.save(user);
        return UserMapper.toUserDto(addUser);
    }

    public UserDto updateUser(UserDto userDtoToUpdate ) {
        Optional<User> wrappedUserByPesel = userRepository.findByPesel(userDtoToUpdate.getPesel());
        wrappedUserByPesel.ifPresent(user -> {
            if (user.getId()!= userDtoToUpdate.getId()) {
                throw new DuplicatePeselException();
            }
        });
        User updatedUser = userRepository.save(UserMapper.toUser(userDtoToUpdate));
        return UserMapper.toUserDto(updatedUser);
    }
}
