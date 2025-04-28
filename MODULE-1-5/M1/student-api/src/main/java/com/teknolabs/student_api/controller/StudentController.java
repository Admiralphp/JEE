package com.teknolabs.student_api.controller;

import com.teknolabs.student_api.model.Student;
import com.teknolabs.student_api.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
