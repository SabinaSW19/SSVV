package domain;

import java.util.Objects;

public class Student implements HasID<String> {
    private String idStudent;
    private String nume;
    private int grupa;
    private String email;
    private String professorName;

    public Student(String idStudent, String nume, int grupa,String email, String professorName) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.grupa = grupa;
        this.email=email;
        this.professorName=professorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    @Override
    public String getID() { return idStudent; }

    @Override
    public void setID(String idStudent) { this.idStudent = idStudent; }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    @Override
    public String toString() {
        return "Student{" + "idStudent=" + idStudent + ", nume='" + nume + '\'' + ", grupa=" + grupa + '\''+", email=" + email + '\''+", nume profesor=" + professorName + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(idStudent, student.idStudent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent);
    }
}

