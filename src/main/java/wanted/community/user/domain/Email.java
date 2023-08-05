package wanted.community.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Email {
    private final String value;

    public Email(String value) {
        this.value = value;
    }


    public static Email create(String mail) {
        checkEmailFormat(mail);
        return new Email(mail);
    }

    private static void checkEmailFormat(String mail) {
        if (mail == null || !mail.contains("@")) {
            throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
        }
    }

    public static Email of(String email) {
        return new Email(email);
    }
}
