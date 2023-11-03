package pl.javastart.equipy.users;

import org.modelmapper.ModelMapper;

class UserMapper {
    static UserDto toUserDto(User user) {
/*        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPesel(user.getPesel());*/
//        return dto;
        return new ModelMapper().map(user, UserDto.class );
    }
    static User toUser(UserDto userDto) {
/*        User entity = new User();
        entity.setId(userDto.getId());
        entity.setFirstName(userDto.getFirstName());
        entity.setLastName(userDto.getLastName());
        entity.setPesel(userDto.getPesel());
        return entity;*/
        return new ModelMapper().map( userDto, User.class );
    }

    static User toUser(NewUserDto newUserDto) {
        return new ModelMapper().map( newUserDto, User.class );
    }
}

