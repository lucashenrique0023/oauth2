package lab.lhss.shcoolauthserver.core.security;

import lab.lhss.shcoolauthserver.domain.model.Operator;
import lab.lhss.shcoolauthserver.domain.model.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    OperatorRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Operator operator = studentRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found."));

        return new AuthUser(operator);

    }

}
