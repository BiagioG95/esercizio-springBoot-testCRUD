package com.example.esercizio_springBoot_testCRUD.controller;

import com.example.esercizio_springBoot_testCRUD.entity.Student;
import com.example.esercizio_springBoot_testCRUD.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/select-all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Long id) {
        Optional<Student> studentOptional = studentService.getStudentById(id);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Student>> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> studentOptional = studentService.updateStudentId(id, student);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/working")
    public Optional<Student> updateWorkingStatus(@PathVariable Long id, @RequestParam boolean working) {
        return studentService.updateWorkingStatus(id, working);
    }

    @DeleteMapping("/{id}")
    public Student deleteStudent(@RequestBody Student student){
        return studentService.deleteStudent(student);
    }
}
