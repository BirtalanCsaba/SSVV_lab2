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

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

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

    @Test(expected = ValidationException.class)
    public void addAssignmentShouldThrowWhenIdNull() {
        service.saveTema(null, "test desc", 4, 2);
        fail();
    }

    @Test(expected = ValidationException.class)
    public void addAssignmentShouldThrowWhenDescriptionNull() {
        service.saveTema("2", null, 4, 2);
        fail();
    }

    @Test(expected = ValidationException.class)
    public void addAssignmentShouldThrowWhenDeadlineOutOfBounds() {
        service.saveTema("2", "test desc", 15, 2);
        fail();
    }

    @Test(expected = ValidationException.class)
    public void addAssignmentShouldThrowWhenStartLineOutOfBounds() {
        service.saveTema("2", "test desc", 1, 0);
        fail();
    }

    @Test
    public void canAddAssignment() {
        assertEquals(service.saveTema("22", "test desc", 4, 2), 1);
    }

    @Test
    public void shouldReturn1WhenAssignmentWithTheSameIdIsAlreadyAdded() {
        service.saveTema("2", "test desc", 4, 2);
        assertEquals(service.saveTema("2", "test desc", 4, 2), 0);
    }

}
