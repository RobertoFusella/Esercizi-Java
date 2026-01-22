package settimana2;

import java.util.Scanner;

public class Confronto {
    public void controllo(int primoValore, int secondoValore) {

            if (primoValore > secondoValore) {
                System.out.println(primoValore + " è maggiore di " + secondoValore);
            }
            else if (primoValore < secondoValore) {
                System.out.println(secondoValore + " è maggiore di " + primoValore);
            }
            else if (primoValore == secondoValore){
                System.out.println("i numeri sono uguali");
            }
    }
    private boolean numeroValidato (int valore){
        if(valore >= 1 && valore <= 100){
        return true;
        }
        else {
            System.out.println("numero non valido deve essere compreso tra 1 e 100");
            return false;
        }
    }
    public int leggiEValida(Scanner scanner) {
        while (true) { // ciclo infinito usciamo solo col return
            System.out.println("Inserisci un numero tra 1 e 100:");
            if (!scanner.hasNextInt()) {
                System.out.println("Errore: non hai inserito un numero intero.");
                scanner.next();
                continue; // Ricomincia il ciclo
            }
            int valore = scanner.nextInt();
            if (numeroValidato(valore)) {
                return valore;
            }
        }
    }
}

