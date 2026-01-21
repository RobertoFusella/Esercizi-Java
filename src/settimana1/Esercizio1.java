package settimana1;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
Scrivi un semplice programma Java che:
legga un valore
esegua un controllo
stampi un risultato
Spiega un errore di compilazione che hai incontrato questa settimana
*/
public class Esercizio1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /*
         TODO riscrivi in modo che se venisse inserito un carattere non numerico,
         viene richiesto il valore invece che terminare con un errore
         */
        try {
            System.out.println("inserisci primo valore");
            int valore1 = scanner.nextInt();
            System.out.println("inserisci secondo valore");
            int valore2 = scanner.nextInt();
            Confronto confronto = new Confronto();
            confronto.controllo(valore1, valore2);
            //un errore che ho incontrato su questo esercizio era l'input
            //se inserivo qualcosa di diverso da un intero (es. un char)
            //mi dava come errore: InputMismatchException
        } catch (InputMismatchException e) {

            /*
             suggerimento in caso di errore bisogna sempre stampare lo stack trace, in questo caso
             e.printStackTrace();
             */

            System.out.println("valore non valido");
        }
    }
}
