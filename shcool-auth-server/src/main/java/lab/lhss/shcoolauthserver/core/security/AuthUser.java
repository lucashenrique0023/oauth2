package lab.lhss.shcoolauthserver.core.security;

import lab.lhss.shcoolauthserver.domain.model.Operator;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class AuthUser extends User {

    private String fullName;
    private Long userId;

    public AuthUser(Operator operator) {
        super(operator.getEmail(), operator.getPassword(), Collections.emptyList());
        this.userId = operator.getId();
        this.fullName = operator.getName();
    }

    public String getFullName() {
        return fullName;
    }

    public Long getUserId() {
        return userId;
    }
}
