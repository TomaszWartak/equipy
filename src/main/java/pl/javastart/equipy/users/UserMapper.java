package pl.javastart.equipy.users;

import org.modelmapper.ModelMapper;

class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }
    static UserDto toUserDto(User user) {
        return new ModelMapper().map(user, UserDto.class );
    }
    static User toUser(UserDto userDto) {
        return new ModelMapper().map( userDto, User.class );
    }
}

