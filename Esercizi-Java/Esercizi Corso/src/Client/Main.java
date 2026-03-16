package Client;

import Model.Student;
import Server.StudentHttp;
import Server.StudentServer;
import Service.StudentService;

import java.util.logging.Level;
import java.util.logging.Logger;
/*
Qui fare pratica con:

HttpClient

HttpRequest

GET request
 */

/*
JDBC Aggiungi:FATTO findAll(),FATTO delete(id), FATTO update voto

REST Aggiungi endpoints:GET /students,GET /students/{id},POST /students,DELETE /students/{id}

HTTP Scrivi client che:FATTO inserisce studente,FATTO legge studenti,FATTO elimina studente
*/
public class Main {
    public static void main(String[] args) {
        final Logger LOGGER = Logger.getLogger(Main.class.getName());
        StudentServer studentServer = new StudentServer("http://localhost:8000/students");
//        studentService.addStudent(new Student(1,"Roberto",6));
//        studentService.addStudent(new Student(2,"Rodrigo",4));
//        studentService.addStudent(new Student(3,"Antonio",3));
        //studentService.addStudent(new Student(1,"Roberto",6));//duplicato
        //studentService.getAll().forEach(System.out::println);

        //System.out.println(studentService.mediaStudent());
        //System.out.println(studentService.getById(1));
        //System.out.println(studentService.votoPiuAlto());
        //studentService.delete(2);
        //studentService.getAll().forEach(System.out::println);

        try {
            Student student = new Student("Marty",10);
            //studentRepository.save(student);
            //studentRepository.delete(9);
            //studentRepository.updateVoto(10,30);
            //studentService.loadFromDB(studentRepository.findAll());
            //System.out.println(studentService.getAll());
            studentServer.updateStudentVoto(8,30);
        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, "errore nel salvataggio studente", ex);

        }
    }
}
