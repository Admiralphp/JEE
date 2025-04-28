package com.teknolabs.student_api.repository;

import com.teknolabs.student_api.model.Student;
import com.teknolabs.student_api.model.StudentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    // Find by email (unique)
    Optional<Student> findByEmail(String email);
    
    // Find by name containing (case-insensitive)
    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
    // Find by status
    List<Student> findByStatus(StudentStatus status);
    
    // Find by status and order by name
    List<Student> findByStatusOrderByNameAsc(StudentStatus status);
    
    // Find students by birth date range
    List<Student> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);
    
    // Count students by status
    long countByStatus(StudentStatus status);
    
    // Custom JPQL query to find active students with pagination
    @Query("SELECT s FROM Student s WHERE s.status = 'ACTIVE'")
    Page<Student> findActiveStudents(Pageable pageable);
    
    // Custom query to search students by name or email
    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Student> searchStudents(@Param("keyword") String keyword, Pageable pageable);
    
    // Update student status
    @Modifying
    @Query("UPDATE Student s SET s.status = :status WHERE s.id = :id")
    int updateStudentStatus(@Param("id") Long id, @Param("status") StudentStatus status);
    
    // Find students by multiple statuses
    List<Student> findByStatusIn(List<StudentStatus> statuses);
}

