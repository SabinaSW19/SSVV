package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Assert;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    private int MAXINT = 100000;

    @Test
    void test_idNegative() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        Student student = new Student("-1", "aa", 222, "abcd@yahoo.com", "sdsds");
        try {
            service.saveStudent("-1", "aa", 222, "abcd@yahoo.com", "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_idExceedsValue() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        Student student = new Student("-1", "aa", 222, "abcd@yahoo.com", "sdsds");
        try {
            service.saveStudent("10000000", "aa", 222, "abcd@yahoo.com", "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_idNotNaturalNumber() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        Student student = new Student("-1", "aa", 222, "abcd@yahoo.com", "sdsds");
        try {
            service.saveStudent("1.5", "aa", 222, "abcd@yahoo.com", "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_idAlreadyExists() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        try {
            service.saveStudent("1", "aa", 222, "abcd@yahoo.com", "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_wrongNameFormat() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        try {
            service.saveStudent("10", "jorge*&^", 222, "abcd@yahoo.com", "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_emptyName() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "", 222, "abcd@yahoo.com", "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_nameTooLong() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        String name = "";
        for (int i = 0; i < 300; i++)
            name += "s";
        service.deleteStudent("10");
        try {
            service.saveStudent("10", name, 222, "abcd@yahoo.com", "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_groupNegative() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "taylor swift", -1, "abcd@yahoo.com", "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_groupTooLarge() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "taylor swift", 1000000000, "abcd@yahoo.com", "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_emailContainsNoAT() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "taylor swift", 1, "a.aaabannn", "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_emailContainsNoPoint() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "taylor swift", 1, "a@aaabannn", "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_emailHasWrongLengthLess() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "taylor swift", 1, "a@n.c", "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_emailHasWrongLengthMore() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        String email = "";
        for (int i = 0; i < 300; i++)
            email += "a";
        email += "@a.com";
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "taylor swift", 1, email, "sdsds");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_professorNameEmpty() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "taylor swift", 1, "aaaa@yahoo.com", "");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_professorNameTooLong() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        String name = "";
        for (int i = 0; i < 300; i++)
            name += "a";

        try {
            service.saveStudent("10", "taylor swift", 1, "aaaa@yahoo.com", name);
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_wrongProfessorNameFormat() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "bbbb", 222, "abcd@yahoo.com", "jorge*&^");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }

    }

    @Test
    void test_correctStudent() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "bbbb", 222, "abcd@yahoo.com", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }

    }

    @Test
    void test_correctStudentWithId0() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("0");
        try {
            service.saveStudent("0", "bbbb", 222, "abcd@yahoo.com", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }

    }

    @Test
    void test_correctStudentWithIdMaxIntMinusOne() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent(String.valueOf(MAXINT - 1));
        try {
            service.saveStudent(String.valueOf(MAXINT - 1), "bbbb", 222, "abcd@yahoo.com", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }

    }

    @Test
    void test_correctStudentWithIdMaxInt() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent(String.valueOf(MAXINT));
        try {
            service.saveStudent(String.valueOf(MAXINT), "bbbb", 222, "abcd@yahoo.com", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }

    }

    @Test
    void test_correctStudentWithNameWithLengthOne() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "a", 222, "abcd@yahoo.com", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_correctStudentWithNameWithLengthTwoHundredFiftyFour() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        String name = "";
        for (int i = 0; i < 254; i++) {
            name += "a";
        }
        try {
            service.saveStudent("10", name, 222, "abcd@yahoo.com", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_wrongStudentWithNameWithLengthTwoHundredFiftySix() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        String name = "";
        for (int i = 0; i < 256; i++) {
            name += "a";
        }
        try {
            service.saveStudent("10", name, 222, "abcd@yahoo.com", "jorge");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }
    }

    @Test
    void test_correctStudentWithName() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 222, "abcd@yahoo.com", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_wrongStudentWithNameWithNonLettersAndLetter() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "1111a", 222, "abcd@yahoo.com", "jorge");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }
    }

    @Test
    void test_wrongStudentWithNameWithNonLetters() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "11111", 222, "abcd@yahoo.com", "jorge");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }
    }

    @Test
    void test_correctStudentWithEmail() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 222, "aaa@bb.c", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_correctStudentWithEmail2() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 222, "aaaa@bb.c", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_correctStudentWithLeghth254() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        String email="";
        for(int i=0;i<249;i++){
            email+="a";
        }
        email+="@bb.c";
        try {
            service.saveStudent("10", "sabina", 222, email, "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_correctStudentWithLeghth255() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        String email="";
        for(int i=0;i<250;i++){
            email+="a";
        }
        email+="@bb.c";
        try {
            service.saveStudent("10", "sabina", 222, email, "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_wrongStudentWithEmailWithMoreAT() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 222, ".@@@@@@@", "jorge");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }
    }

    @Test
    void test_wrongStudentWithEmailWithOnlyAT() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 222, "@@@@@@@", "jorge");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }
    }

    @Test
    void test_wrongStudentWithEmailWithMorePoints() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 222, ".......@", "jorge");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }
    }

    @Test
    void test_wrongStudentWithEmailWithOnlyPoints() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 222, "........", "jorge");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }
    }

    @Test
    void test_correctStudentWithGroup0() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 0, "abcd@a.com", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_correctStudentWithGroup1() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 1, "abcd@a.com", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_correctStudentWithMaxIntMinus1() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", MAXINT-1, "abcd@a.com", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_correctStudentWithMaxIntMinus() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", MAXINT, "abcd@a.com", "jorge");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_correctStudentWithProfNameWithLength1() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 222, "abcd@a.com", "a");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_correctStudentWithProfNameWithLength4() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 222, "abcd@a.com", "aaaa");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_correctStudentWithProfNameWithLength254() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        String name="a";
        for(int i=0;i<254;i++){
            name+="a";
        }
        try {
            service.saveStudent("10", "sabina", 222, "abcd@a.com", name);
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }
    }

    @Test
    void test_wrongStudentWithProfNameWithLength256() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        String name="a";
        for(int i=0;i<256;i++){
            name+="a";
        }
        try {
            service.saveStudent("10", "sabina", 222, "abcd@a.com", name);
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }
    }

    @Test
    void test_wrongStudentWithProfNameWithDigitsAndLetter() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 222, "abcd@a.com", "1111a1");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }
    }

    @Test
    void test_wrongStudentWithProfNameWithDigits() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("10");
        try {
            service.saveStudent("10", "sabina", 222, "abcd@a.com", "111111");
            assert (false);
        } catch (ValidationException e) {
            assert (true);
        }
    }

}
