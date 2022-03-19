package lab.lhss.shcoolauthserver.core.security;

import lab.lhss.shcoolauthserver.domain.model.Student;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class AuthUser extends User {

    private String fullName;

    public AuthUser(Student student) {
        super(student.getEmail(), student.getPassword(), Collections.emptyList());

        this.fullName = student.getName();
    }

}
