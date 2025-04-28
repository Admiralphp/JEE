INSERT INTO students (name, email, date_of_birth, phone_number, student_status, created_date, created_by)
VALUES 
    ('John Doe', 'john.doe@example.com', '2000-01-15', '+1234567890', 'ACTIVE', CURRENT_TIMESTAMP(), 'SYSTEM'),
    ('Jane Smith', 'jane.smith@example.com', '2001-03-21', '+1234567891', 'ACTIVE', CURRENT_TIMESTAMP(), 'SYSTEM'),
    ('Alice Johnson', 'alice.j@example.com', '1999-12-05', '+1234567892', 'GRADUATED', CURRENT_TIMESTAMP(), 'SYSTEM'),
    ('Bob Wilson', 'bob.wilson@example.com', '2002-07-30', '+1234567893', 'ACTIVE', CURRENT_TIMESTAMP(), 'SYSTEM');