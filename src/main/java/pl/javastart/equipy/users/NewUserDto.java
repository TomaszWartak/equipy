package pl.javastart.equipy.users;

public class NewUserDto {
    private String firstName;
    private String lastName;
    private String pesel;

    public NewUserDto() {
        // Konstruktor domyślny
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
}
