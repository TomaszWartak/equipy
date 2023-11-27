package pl.javastart.equipy.users;

import org.modelmapper.ModelMapper;

public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static UserDto toUserDto(User user) {
        return new ModelMapper().map(user, UserDto.class );
    }
    public static User toUser(UserDto userDto) {
        return new ModelMapper().map( userDto, User.class );
    }
}

