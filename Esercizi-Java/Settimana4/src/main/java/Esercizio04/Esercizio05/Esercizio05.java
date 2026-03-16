package Esercizio04.Esercizio05;

public class Esercizio05 {
    static void main(String[] args) throws InterruptedException {
        Allarme allarme = new Allarme("temperatura troppo alta");
        allarme.accendiAllarme();
        allarme.staDormendo();
        Thread.sleep(5000);
        allarme.inviaNotifica();
    }
}