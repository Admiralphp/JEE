CREATE TABLE students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    date_of_birth DATE,
    phone_number VARCHAR(20),
    student_status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_date TIMESTAMP NOT NULL,
    last_modified_date TIMESTAMP,
    created_by VARCHAR(50) NOT NULL,
    last_modified_by VARCHAR(50)
);

CREATE INDEX idx_student_email ON students(email);
CREATE INDEX idx_student_status ON students(student_status);