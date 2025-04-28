package com.teknolabs.student_api.service;

import com.teknolabs.student_api.exception.ResourceNotFoundException;
import com.teknolabs.student_api.model.Student;
import com.teknolabs.student_api.model.StudentStatus;
import com.teknolabs.student_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Student> getAllStudentsPaged(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Student> searchStudents(String keyword, Pageable pageable) {
        return studentRepository.searchStudents(keyword, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Student> findByNameContaining(String name, Pageable pageable) {
        return studentRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Transactional(readOnly = true)
    public List<Student> findByStatus(StudentStatus status) {
        return studentRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Student> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate) {
        return studentRepository.findByDateOfBirthBetween(startDate, endDate);
    }

    @Transactional(readOnly = true)
    public long countByStatus(StudentStatus status) {
        return studentRepository.countByStatus(status);
    }

    @Transactional
    public boolean updateStudentStatus(Long id, StudentStatus status) {
        return studentRepository.updateStudentStatus(id, status) > 0;
    }

    @Transactional(readOnly = true)
    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Page<Student> findActiveStudents(Pageable pageable) {
        return studentRepository.findActiveStudents(pageable);
    }

    @Transactional(readOnly = true)
    public List<Student> findByStatusOrdered(StudentStatus status) {
        return studentRepository.findByStatusOrderByNameAsc(status);
    }

    @Transactional(readOnly = true)
    public List<Student> findByMultipleStatuses(List<StudentStatus> statuses) {
        return studentRepository.findByStatusIn(statuses);
    }
}