package org.esempio.Model;

// Classe modello (POJO) che rappresenta uno Speaker
public class Speaker {

    // Proprietà private
    private String firstName;
    private String lastName;
    private double seedNum;

    // Getter: restituisce il nome
    public String getFirstName() {
        return firstName;
    }

    // Setter: imposta il nome
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter: restituisce il cognome
    public String getLastName() {
        return lastName;
    }

    // Setter: imposta il cognome
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter: restituisce il valore seed
    public double getSeedNum() {
        return seedNum;
    }

    // Setter: imposta il valore seed
    public void setSeedNum(double seedNum) {
        this.seedNum = seedNum;
    }
}