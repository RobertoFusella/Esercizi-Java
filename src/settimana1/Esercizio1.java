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
        int valore1;
        int valore2;
        boolean inputNonValido=false;
        /*
         TODO riscrivi in modo che se venisse inserito un carattere non numerico,
         viene richiesto il valore invece che terminare con un errore
         */
        while (!inputNonValido)
            try {
                System.out.println("inserisci primo valore");
                valore1 = scanner.nextInt();
                System.out.println("inserisci secondo valore");
                valore2 = scanner.nextInt();

                Confronto confronto = new Confronto();
                confronto.controllo(valore1, valore2);
                inputNonValido=true;

            } catch (InputMismatchException e) {
                System.out.println("errore riprova");
                scanner.nextLine();
            }
        }
                //un errore che ho incontrato su questo esercizio era l'input
                //se inserivo qualcosa di diverso da un intero (es. un char)
                //mi dava come errore: InputMismatchException

                //un altro errore che ho incontrato è stato che se non pulivo
                //lo scanner con .nextLine() l'input sbagliato rimaneva nel buffer
                //e partiva un loop infinito
    }
