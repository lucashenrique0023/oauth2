package lab.lhss.school.api.controllers;

import lab.lhss.school.domain.model.Student;
import lab.lhss.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{studentId}")
    public Student find(@PathVariable Long studentId) {
        return studentService.findById(studentId);
    }

    @PostMapping
    public Student save(@RequestBody @Valid Student student) {
        return studentService.save(student);
    }

    @DeleteMapping("/{walletId}")
    public void delete(@PathVariable Long studentId) {
        studentService.deleteById(studentId);
    }

}
