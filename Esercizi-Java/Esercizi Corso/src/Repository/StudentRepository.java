package Repository;

import Model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentRepository {
    private static final Logger LOGGER = Logger.getLogger(StudentRepository.class.getName());
    private final String url = "jdbc:mysql://localhost:3307/Student_db";
    private final String user = "root";
    private final String password = "root";

    public void save(Student s) {
        String sql = "INSERT INTO students(nome, voto) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getNome());
            ps.setInt(2, s.getVoto());
            int righe = ps.executeUpdate();
            // Recupera l'id generato dal DB
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    s.setId(rs.getInt(1));
                }
            }
            LOGGER.info("Righe inserite: " + righe + ", id generato: " + s.getId());
            LOGGER.info("Query: INSERT INTO students (nome, voto) VALUES ('" + s.getNome() + "', " + s.getVoto() + ")");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int righe = ps.executeUpdate();

            if (righe > 0) {
                LOGGER.info("Studente con id: " + id + " eliminato. Righe interessate: " + righe);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try(Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int voto = rs.getInt("voto");
                studentList.add(new Student(id, nome, voto));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return studentList;
    }

    public void updateVoto(int id, int voto) {
        String sql = "UPDATE students SET voto = ? WHERE id = ?";
        try(Connection conn = DriverManager.getConnection(url,user,password);
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, voto);
            ps.setInt(2, id);
            int righe = ps.executeUpdate();
            if (righe > 0) {
                LOGGER.info("voto dello studente: " + id + " modificato a: " + voto);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}