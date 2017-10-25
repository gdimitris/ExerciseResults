import java.util.Optional;

public class User {
    private final String firstName;
    private final String lastName;
    private Optional<String> middleName = Optional.empty();

    public User(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String fullName() {
        return firstName + middleName.map(mn -> " " + mn + " ").orElse(" ") + lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = Optional.of(middleName);
    }
}
