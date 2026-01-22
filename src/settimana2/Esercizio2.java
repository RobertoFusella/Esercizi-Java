package settimana2;
import java.util.Scanner;
public class Esercizio2 {
    /*
    TODO
    Usando il codice di settimana1.Esercizio1

    Scrivi un metodo che valida un input (accetta solo interi tra 1 e 100)

    Refactorizza codice duplicato usando metodi

    Spiega come hai migliorato la leggibilità del codice
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Confronto2 confronto = new Confronto2();

        int primoValore = confronto.leggiEValida(scanner);

        int secondoValore = confronto.leggiEValida(scanner);

        confronto.controllo(primoValore, secondoValore);
    }
}
