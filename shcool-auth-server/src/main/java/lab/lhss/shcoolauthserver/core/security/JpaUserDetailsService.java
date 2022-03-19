package lab.lhss.shcoolauthserver.core.security;

import lab.lhss.shcoolauthserver.domain.model.Student;
import lab.lhss.shcoolauthserver.domain.model.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Student student = studentRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found."));

        return new AuthUser(student);

    }

}
