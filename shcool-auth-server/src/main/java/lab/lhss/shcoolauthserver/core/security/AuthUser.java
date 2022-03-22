package lab.lhss.shcoolauthserver.core.security;

import lab.lhss.shcoolauthserver.domain.model.Operator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthUser extends User {

    private String fullName;
    private Long userId;

    public AuthUser(Operator operator, Collection <? extends GrantedAuthority > authorities) {
        super(operator.getEmail(), operator.getPassword(), authorities);
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
