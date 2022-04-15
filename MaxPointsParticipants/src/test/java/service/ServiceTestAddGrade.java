package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.*;

public class ServiceTestAddGrade {

    @Test
    public void test_addStudent() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        Student student = new Student("1", "aa", 222, "abcd@yahoo.com", "sdsds");
        service.deleteStudent("100");
        try {
            service.saveStudent("100", "aa", 222, "abcd@yahoo.com", "sdsds");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }

    }
    @Test
    public void test_addAssignment() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteTema("100");
        try {
            service.saveTema("100","aaa",12,11);
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }

    }
    @Test
    public void test_addGrade() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteNota("100","100");

        try {
            service.saveNota("100","100",10,12,"fantastic");
            assert (true);
        } catch (ValidationException e) {
            assert (false);
        }

    }
    @Test
    public void test_BigBang()
    {
        test_addStudent();
        test_addAssignment();
        test_addGrade();
    }
}
