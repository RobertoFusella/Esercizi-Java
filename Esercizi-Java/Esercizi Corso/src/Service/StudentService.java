package Service;

import Model.Student;
import Repository.StudentRepository;

import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class StudentService {

    private final StudentRepository studentRepository = new StudentRepository();

    public void addStudent(Student s) {
        studentRepository.save(s);
    }

    public void editVoto(int id, int voto) {
        studentRepository.updateVoto(id, voto);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(int id) {
        return studentRepository.findById(id);
    }

    public void delete(int id) {
        studentRepository.delete(id);
    }

    public int mediaStudent() {
        return (int) studentRepository.findAll()
                .stream()
                .mapToInt(Student::getVoto)
                .average()
                .orElse(0);
    }

    public Student votoPiuAlto() {
        return studentRepository.findAll()
                .stream()
                .max(Comparator.comparingInt(Student::getVoto))
                .orElse(null);
    }
}