package Model;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class User {
    private String firstname;
    private String lastname;
    private UUID id;

    public User() {
        // empty constructor
    }

    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = randomUUID();

    }

    // getters
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
