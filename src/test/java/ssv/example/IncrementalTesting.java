package ssv.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ssv.example.domain.Student;
import ssv.example.domain.Tema;
import ssv.example.repository.NotaXMLRepository;
import ssv.example.repository.StudentXMLRepository;
import ssv.example.repository.TemaXMLRepository;
import ssv.example.service.Service;
import ssv.example.validation.NotaValidator;
import ssv.example.validation.StudentValidator;
import ssv.example.validation.TemaValidator;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IncrementalTesting {

    StudentValidator studentValidatorInt;

    TemaValidator temaValidatorInt;

    NotaValidator notaValidatorInt;

    @Mock
    private StudentXMLRepository mockStudentXMLRepository;

    private StudentXMLRepository studentXMLRepositoryInt;

    @Mock
    private TemaXMLRepository mockTemaXMLRepository;

    private TemaXMLRepository temaXMLRepositoryInt;

    private NotaXMLRepository notaXMLRepositoryInt;

    @InjectMocks
    private Service mockService;

    private Service serviceInt;

    @Before
    public void setup() {
        studentValidatorInt = new StudentValidator();
        studentXMLRepositoryInt = new StudentXMLRepository(studentValidatorInt, "studenti_test.xml");

        temaValidatorInt = new TemaValidator();
        temaXMLRepositoryInt = new TemaXMLRepository(temaValidatorInt, "teme_test.xml");

        notaValidatorInt = new NotaValidator();
        notaXMLRepositoryInt = new NotaXMLRepository(notaValidatorInt, "note_test.xml");

        serviceInt = new Service(
                studentXMLRepositoryInt,
                temaXMLRepositoryInt,
                notaXMLRepositoryInt
        );
    }

    @Test
    public void canAddAssignmentMock() {
        Tema expectedTema = new Tema("1", "desc", 2, 5);

        when(mockTemaXMLRepository.save(expectedTema)).thenReturn(null);

        assertEquals(mockService.saveTema("1", "desc", 2, 5), 1);
    }

    @Test
    public void shouldReturn0WhenAssignmentAlreadyExistsMock() {
        Tema expectedTema = new Tema("1", "desc", 2, 5);

        when(mockTemaXMLRepository.save(expectedTema)).thenReturn(expectedTema);

        assertEquals(mockService.saveTema("1", "desc", 2, 5), 0);
    }

    @Test
    public void canAddStudentMock() {
        Student expectedStudent = new Student("1", "test", 931);

        when(mockStudentXMLRepository.save(expectedStudent)).thenReturn(null);

        assertEquals(mockService.saveStudent("1", "test", 931), 1);
    }

    @Test
    public void shouldReturn0WhenStudentAlreadyExistsMock() {
        Student expectedStudent = new Student("1", "test", 931);

        when(mockStudentXMLRepository.save(expectedStudent)).thenReturn(expectedStudent);

        assertEquals(mockService.saveStudent("1", "test", 931), 0);
    }

    @Test
    public void canAddAssignment() {
        assertEquals(serviceInt.saveTema(UUID.randomUUID().toString(), "test desc", 4, 2), 1);
        assertTrue(true);
    }

    @Test
    public void canAddStudent() {
        assertEquals(serviceInt.saveStudent(UUID.randomUUID().toString(), "Student1", 935), 1);
        assertTrue(true);
    }

    @Test
    public void canAddStudentAndAssignment() {
        assertEquals(serviceInt.saveTema(UUID.randomUUID().toString(), "test desc", 4, 2), 1);
        assertTrue(true);

        assertEquals(serviceInt.saveStudent(UUID.randomUUID().toString(), "Student1", 935), 1);
        assertTrue(true);
    }

    @Test
    public void canAddGrade() {
        String temaId = UUID.randomUUID().toString();
        String studentId = UUID.randomUUID().toString();

        assertEquals(serviceInt.saveTema(temaId, "test desc", 4, 2), 1);
        assertEquals(serviceInt.saveStudent(studentId, "Student1", 935), 1);

        assertEquals(serviceInt.saveNota(studentId, temaId, 10, 2, "test feedback"), 1);
    }
}
