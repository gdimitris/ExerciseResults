import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserStore {
    List<User> store = new ArrayList<>();

    public void add(User user) {
        store.add(user);
    }

    public Optional<User> firstByFirstName(String firstName) {
        return store.stream()
                .filter(u -> u.getFirstName().equals(firstName))
                .findFirst();
    }
}
