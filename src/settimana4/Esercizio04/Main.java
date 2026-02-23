package settimana4.Esercizio04;

public class Main {
    static void main(String[] args) {
        Allarme allarme = new Allarme("temperatura troppo alta");
        allarme.accendiAllarme();
        allarme.inviaNotifica();
    }
}