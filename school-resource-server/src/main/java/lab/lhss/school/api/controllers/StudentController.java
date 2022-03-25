package lab.lhss.school.api.controllers;

import lab.lhss.school.core.security.CheckSecurity;
import lab.lhss.school.domain.model.Student;
import lab.lhss.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{studentId}")
    @CheckSecurity.Student.Search
    public Student search(@PathVariable Long studentId) {
        return studentService.findById(studentId);
    }

    @PostMapping
    @CheckSecurity.Student.Create
    public Student create(@RequestBody @Valid Student student) {
        return studentService.save(student);
    }

    @DeleteMapping("/{walletId}")
    @CheckSecurity.Student.Delete
    public void delete(@PathVariable Long studentId) {
        studentService.deleteById(studentId);
    }

}
