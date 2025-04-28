package com.teknolabs.student_api.controller;

import com.teknolabs.student_api.exception.ResourceNotFoundException;
import com.teknolabs.student_api.model.Student;
import com.teknolabs.student_api.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Tag(name = "Student", description = "Student management APIs")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Web UI endpoints
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/list";
    }

    @GetMapping("/students/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @GetMapping("/students/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        model.addAttribute("student", student);
        return "students/form";
    }

    // REST API endpoints
    @Operation(summary = "Get all students", description = "Returns a list of all students in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved students")
    @GetMapping("/api/students")
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "Get a student by ID", description = "Returns a student based on the ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved student"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/api/students/{id}")
    @ResponseBody
    public ResponseEntity<Student> getStudentById(
        @Parameter(description = "ID of the student to retrieve", required = true)
        @PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Operation(summary = "Create a new student", description = "Creates a new student in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Student created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/api/students")
    @ResponseBody
    public ResponseEntity<Student> createStudent(
        @Parameter(description = "Student to create", required = true)
        @Valid @RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @Operation(summary = "Update a student", description = "Updates an existing student in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student updated successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/api/students/{id}")
    @ResponseBody
    public ResponseEntity<Student> updateStudent(
        @Parameter(description = "ID of the student to update", required = true)
        @PathVariable Long id,
        @Parameter(description = "Updated student information", required = true)
        @Valid @RequestBody Student student) {
        student.setId(id);
        Student updatedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(summary = "Delete a student", description = "Deletes a student from the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/api/students/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteStudent(
        @Parameter(description = "ID of the student to delete", required = true)
        @PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // Form submission endpoints
    @PostMapping("/students")
    public String createStudentForm(@Valid @ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @PostMapping("/students/{id}")
    public String updateStudentForm(@PathVariable Long id, @Valid @ModelAttribute Student student) {
        student.setId(id);
        studentService.saveStudent(student);
        return "redirect:/students";
    }
}
