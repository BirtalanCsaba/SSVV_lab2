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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IntegrationTest {
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
        assertEquals(service.saveTema("4", "test desc", 4, 2), 0);
    }

    @Test
    public void canAddStudent() {
        assertEquals(service.saveStudent("1", "Student1", 935), 0);
    }

    @Test
    public void canAddGrade() {
        assertEquals(service.saveNota(
                "2",
                "2",
                2,
                1,
                "test feedback"
                ),
                0
        );
    }

    @Test
    public void canBigBang() {
        assertEquals(service.saveTema("8", "test desc", 4, 2), 0);
        assertEquals(service.saveStudent("11", "Student1", 935), 0);
        assertEquals(service.saveNota(
                        "11",
                        "8",
                        2,
                        1,
                        "test feedback"
                ),
                0
        );
    }
}
