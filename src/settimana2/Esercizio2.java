package settimana2;
import settimana1.Confronto;
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
        int primoValore;
        int secondoValore;
        boolean inputNonValido = false;
        Confronto confronto = new Confronto();

        do {
            System.out.println("Inserisci primo valore");
            if (!scanner.hasNextInt()) {
                System.out.println("Errore, riprova:");
                scanner.next();
            }
            primoValore = scanner.nextInt();
            System.out.println("Inserisci secondo valore");
            if (!scanner.hasNextInt()) {
                System.out.println("Errore, riprova:");
                scanner.next();
            }
            secondoValore = scanner.nextInt();
            inputNonValido = true;
        } while (!inputNonValido);


        confronto.controllo(primoValore, secondoValore);
    }
}
