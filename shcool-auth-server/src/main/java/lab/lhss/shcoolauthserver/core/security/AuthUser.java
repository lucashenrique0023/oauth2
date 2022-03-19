package lab.lhss.shcoolauthserver.core.security;

import lab.lhss.shcoolauthserver.domain.model.Student;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class AuthUser extends User {

    private String fullName;
    private Long userId;

    public AuthUser(Student student) {
        super(student.getEmail(), student.getPassword(), Collections.emptyList());
        this.userId = student.getId();
        this.fullName = student.getName();
    }

    public String getFullName() {
        return fullName;
    }

    public Long getUserId() {
        return userId;
    }
}
