package com.example.esercizio_springBoot_testCRUD.service;

import com.example.esercizio_springBoot_testCRUD.entity.Student;
import com.example.esercizio_springBoot_testCRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student); }

    public List<Student> getAllStudents() {
        return studentRepository.findAll(); }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id); }

    public Student deleteStudent(Student student){
       studentRepository.delete(student);
        return student;

    }
    public Optional<Student> updateStudentId(Long id, Student updateStudent) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            student.get().setName(updateStudent.getName());
             student.get().setSurname(updateStudent.getSurname());
             student.get().setWorking(updateStudent.isWorking());
            Student student1 = studentRepository.save(student.get());
            return Optional.of(student1);
        } else{
            return Optional.empty();
        }
    }

    public Optional<Student> updateWorkingStatus(Long id, boolean working) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setWorking(working);
            studentRepository.save(student);
            return Optional.of(student);
        } else {
            return Optional.empty();
        }
    }
}



