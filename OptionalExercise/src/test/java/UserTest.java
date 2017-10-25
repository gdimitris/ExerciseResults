import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void itPrintsTheUsersFirstAndLastName() {
        Address a = new Address("", "60047");
        User u = new User("Eric", "Smith", a);

        assertEquals("Eric Smith", u.fullName());
    }

    @Test
    public void itPrintsTheUsersFullName() {
        Address a = new Address("", "");
        User u = new User("Eric", "Smith", a);
        u.setMiddleName("Martin");

        assertEquals("Eric Martin Smith", u.fullName());
    }
}
