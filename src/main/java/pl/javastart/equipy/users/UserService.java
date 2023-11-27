package pl.javastart.equipy.users;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.assignments.Assignment;
import pl.javastart.equipy.assignments.AssignmentDto;
import pl.javastart.equipy.assignments.AssignmentMapper;

import java.util.ArrayList;
import java.util.List;
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

    @Transactional
    public List<AssignmentDto> getAssignmentsForUserId(Long userId ) {
        Optional<User> userFoundWrapped = userRepository.findById( userId );
        return /*(ArrayList<AssignmentDto>)*/ userFoundWrapped
                .map( User::getAssignments )
                .orElseThrow( UserNotFoundException::new )
                .stream()
                .map( AssignmentMapper::toAssignmentDto)
                .collect(Collectors.toList());
    }
}
