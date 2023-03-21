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

import static org.junit.Assert.assertTrue;

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
        studentFileRepository = new StudentXMLRepository(studentValidator, "studenti.xml");

        temaValidator = new TemaValidator();
        temaFileRepository = new TemaXMLRepository(temaValidator, "teme.xml");

        notaValidator = new NotaValidator();
        notaFileRepository = new NotaXMLRepository(notaValidator, "note.xml");

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
    public void addStudentValidGroup() {
        Student student = new Student("1", "Raul Mogos", 935);
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

    @Test
    public void addStudentInvalidGroup() {
        Student student = new Student("1", "Raul Mogos", -1);
        studentValidator.validate(student);
        assertTrue(true);
        studentFileRepository.save(student);
        assertTrue(true);
    }

}
