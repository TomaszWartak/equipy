package pl.javastart.equipy.users;

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;

    public UserDto() {
        // Konstruktor domyślny
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public static class UserDtoBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String pesel;

        public UserDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDtoBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }


        public UserDtoBuilder pesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public UserDto build() {
            UserDto userDto = new UserDto();
            userDto.id = this.id;
            userDto.firstName = this.firstName;
            userDto.lastName = this.lastName;
            userDto.pesel = this.pesel;
            return userDto;
        }
    }

    public static UserDtoBuilder builder() {
        return new UserDtoBuilder();
    }

}
