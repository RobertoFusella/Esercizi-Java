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
        int primoValore;
        int secondoValore;
        boolean inputNonValido = false;
        /*
         riscrivi in modo che se venisse inserito un carattere non numerico,
         viene richiesto il valore invece che terminare con un errore -> FATTO
         TODO
         con il codice attuale anche se sbagli il secondo numero, devi rimettere anche il primo,
         riesci a fare in modo che non succede?
         TODO
         il while è tecnicamente corretto, ma non formalmente, c'è un'istruzione che è fatta apposta
         per eseguire un blocco di codice almeno una volta, prova a usare quella
         */
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

        Confronto confronto = new Confronto();
        confronto.controllo(primoValore, secondoValore);
        //un errore che ho incontrato su questo esercizio era l'input
        //se inserivo qualcosa di diverso da un intero (es. un char)
        //mi dava come errore: InputMismatchException

        //un altro errore che ho incontrato è stato che se non pulivo
        //lo scanner con .nextLine() l'input sbagliato rimaneva nel buffer
        //e partiva un loop infinito
    }
}
