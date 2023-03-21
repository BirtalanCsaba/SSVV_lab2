package ssv.example.repository;

import ssv.example.domain.Student;
import ssv.example.validation.Validator;

public class StudentRepository extends AbstractCRUDRepository<String, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

