package validation;

import domain.Student;

public class StudentValidator implements Validator<Student> {
    private final int MAXINT = 100000;

    public void validate(Student student) throws ValidationException {

        if (student.getID().contains(".")) {
            throw new ValidationException("ID invalid! \n");
        }
        if (student.getID().contains(".")) {
            throw new ValidationException("ID invalid! \n");
        }
        if (student.getID() == null || student.getID().equals("") || Integer.parseInt(student.getID()) < 0 || Integer.parseInt(student.getID()) > MAXINT) {
            throw new ValidationException("ID invalid! \n");
        }
        if (student.getNume() == null || student.getNume().equals("")) {
            throw new ValidationException("Nume invalid! \n");
        }
        if (!student.getNume().matches("^[a-zA-Z ]+$")) {
            throw new ValidationException("Nume invalid!");
        }
        if (student.getNume().length() > 255) {
            throw new ValidationException("Nume invalid! \n");
        }
        if (student.getGrupa() < 0) {
            throw new ValidationException("Grupa invalida! \n");
        }
        if (student.getGrupa() > MAXINT) {
            throw new ValidationException("Grupa invalida! \n");
        }

        if (student.getEmail().length() < 8) {
            throw new ValidationException("Email invalid!");
        }
        if (student.getEmail().length() > 255) {
            throw new ValidationException("Email invalid!");
        }
        if (!student.getEmail().contains("@") || !student.getEmail().contains(".")) {
            throw new ValidationException("Email invalid!");
        }
        int nr = 0;
        for (int i = 0; i < student.getEmail().length(); i++) {
            if (student.getEmail().charAt(i) == '@') {
                nr++;
            }
        }
        if (nr > 1) {
            throw new ValidationException("Email invalid!");
        }
        int nr2 = 0;
        for (int i = 0; i < student.getEmail().length(); i++) {
            if (student.getEmail().charAt(i) == '.') {
                nr2++;
            }
        }
        if (nr2 > 1) {
            throw new ValidationException("Email invalid!");
        }
        if (student.getProfessorName().equals("")) {
            throw new ValidationException("Nume profesor invalid!");
        }
        if (student.getProfessorName().length() > 255) {
            throw new ValidationException("Nume profesor invalid!");
        }
        if (!student.getProfessorName().matches("^[a-zA-Z ]+$")) {
            throw new ValidationException("Nume profesor invalid!");
        }
    }
}

