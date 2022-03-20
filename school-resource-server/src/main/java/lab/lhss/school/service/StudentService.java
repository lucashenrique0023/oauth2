package lab.lhss.school.service;

import lab.lhss.school.core.security.SchoolSecurity;
import lab.lhss.school.domain.model.Student;
import lab.lhss.school.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SchoolSecurity schoolSecurity;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student save(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        logger.info("User: " + schoolSecurity.getAuthenticatedUsername() + " is adding a new student: " + student.getName());
        return studentRepository.save(student);
    }

    public void deleteById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public Student findById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow();
    }

}
