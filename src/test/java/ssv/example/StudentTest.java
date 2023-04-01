package ssv.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ssv.example.domain.Student;
import ssv.example.repository.NotaXMLRepository;
import ssv.example.repository.StudentXMLRepository;
import ssv.example.repository.TemaXMLRepository;
import ssv.example.service.Service;
import ssv.example.validation.NotaValidator;
import ssv.example.validation.StudentValidator;
import ssv.example.validation.TemaValidator;
import ssv.example.validation.ValidationException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class StudentTest {

    StudentValidator studentValidator;
    StudentXMLRepository studentFileRepository;

    TemaValidator temaValidator;
    TemaXMLRepository temaFileRepository;

    NotaValidator notaValidator;
    NotaXMLRepository notaFileRepository;

    Service service;

    @Before
    public void setUp() throws Exception {
        studentValidator = new StudentValidator();
        studentFileRepository = new StudentXMLRepository(studentValidator, "studenti_test.xml");

        temaValidator = new TemaValidator();
        temaFileRepository = new TemaXMLRepository(temaValidator, "teme_test.xml");

        notaValidator = new NotaValidator();
        notaFileRepository = new NotaXMLRepository(notaValidator, "note_test.xml");

        service = new Service(
            studentFileRepository,
            temaFileRepository,
            notaFileRepository
        );
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void canAddStudent() {
        Student student = new Student("1", "Student1", 935);
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowWhenAddStudentIdNull() {
        Student student = new Student(null, "Student1", 935);
        studentValidator.validate(student);
        fail();
        studentFileRepository.save(student);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowWhenAddStudentIdEmpty() {
        Student student = new Student("", "Student1", 935);
        studentValidator.validate(student);
        fail();
        studentFileRepository.save(student);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowWhenAddStudentNullName() {
        Student student = new Student("1", null, 935);
        studentValidator.validate(student);
        fail();
        studentFileRepository.save(student);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowWhenAddStudentEmptyName() {
        Student student = new Student("1", "", 935);
        studentValidator.validate(student);
        fail();
        studentFileRepository.save(student);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowWhenAddStudentGroupRangeUpperLimitInvalid() {
        Student student = new Student("1", "Student1", 939);
        studentValidator.validate(student);
        fail();
        studentFileRepository.save(student);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowWhenAddStudentGroupRangeLowerLimitInvalid() {
        Student student = new Student("1", "Student1", 109);
        studentValidator.validate(student);
        fail();
        studentFileRepository.save(student);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowWhenAddStudentGroupInvalid() {
        Student student = new Student("1", "Student1", -1);
        studentValidator.validate(student);
        fail();
        studentFileRepository.save(student);
    }

}
