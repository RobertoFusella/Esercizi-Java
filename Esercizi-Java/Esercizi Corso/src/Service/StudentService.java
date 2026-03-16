package Service;

import Model.Student;
import Repository.StudentRepository;

import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class StudentService {

    private static final Logger LOGGER = Logger.getLogger(StudentService.class.getName());

    private final Map<Integer, Student> studentMap = new HashMap<>();
    private final StudentRepository studentRepository = new StudentRepository();
    // Aggiunge uno studente se non esiste già (id come chiave)
    public void addStudent(Student s) {
        studentRepository.save(s);
    }

    public void editVoto(int id,int voto) {
        studentRepository.updateVoto(id, voto);
    }

    // Restituisce tutti gli studenti come lista
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    // Recupera studente per id
    public Student getById(int id) {
        return studentMap.get(id);
    }

    // Elimina studente per id
    public void delete(int id) {
        studentRepository.delete(id);
    }

    // Calcola la media dei voti
    public int mediaStudent() {
        return (int) studentMap.values()
                .stream()
                .mapToInt(Student::getVoto)
                .average()
                .orElse(0);
    }

    // Restituisce lo studente con il voto più alto
    public Student votoPiuAlto() {
        return studentMap.values()
                .stream()
                .max(Comparator.comparingInt(Student::getVoto))
                .orElse(null);
    }

    // Popola la mappa con gli studenti già presenti nel DB
    public void loadFromDB(List<Student> studentiDalDB) {
        for (Student s : studentiDalDB) {
            addStudent(s); // usa il controllo duplicati già presente
        }

    }
}