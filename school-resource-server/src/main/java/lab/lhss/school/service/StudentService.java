package lab.lhss.school.service;

import lab.lhss.school.domain.model.Student;
import lab.lhss.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public Student save(Student student) {
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
