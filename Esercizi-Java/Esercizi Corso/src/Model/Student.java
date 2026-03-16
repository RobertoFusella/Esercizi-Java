package Model;

import java.util.Objects;
public class Student {

    private int id;
    private String nome;
    private int voto;

    public Student(){}

    public Student(int id, String nome, int voto) {
        this.id = id;
        this.nome = nome;
        this.voto = voto;
    }

    public Student(String nome, int voto) {
        this.nome = nome;
        this.voto = voto;
    }


    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getVoto() { return voto; }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    @Override
    public String toString() {
        return id + " " + nome + " " + voto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}