package wanted.community.user.domain;

import java.util.Objects;

public class Password {

    private final String password;

    public Password(String password) {
        this.password = password;
    }

    public static Password create(String password) {
        return new Password(password);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }
}
