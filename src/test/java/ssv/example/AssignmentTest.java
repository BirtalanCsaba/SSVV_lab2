package ssv.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ssv.example.domain.Student;
import ssv.example.domain.Tema;
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

public class AssignmentTest {

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
    public void canAddAssignment() {
        Tema tema = new Tema("1", "TestDescription", 4, 2);
        temaValidator.validate(tema);
    }

    @Test(expected = ValidationException.class)
    public void addAssignmentShouldThrowWhenStartLineLessThenDeadline() {
        Tema tema = new Tema("1", "TestDescription", 2, 4);
        temaValidator.validate(tema);
        fail();
    }

}
