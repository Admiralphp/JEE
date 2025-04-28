package com.teknolabs.student_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "students")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Student entity representing a student in the system")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the student", example = "1")
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Schema(description = "Name of the student", example = "John Doe", required = true)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(unique = true)
    @Schema(description = "Email address of the student", example = "john.doe@example.com", required = true)
    private String email;

    @Column(name = "date_of_birth")
    @Past(message = "Date of birth must be in the past")
    @Schema(description = "Date of birth of the student", example = "2000-01-01")
    private LocalDate dateOfBirth;

    @Column(length = 20)
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid")
    @Schema(description = "Phone number of the student", example = "+1234567890")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_status")
    @Schema(description = "Current status of the student")
    private StudentStatus status = StudentStatus.ACTIVE;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
}
