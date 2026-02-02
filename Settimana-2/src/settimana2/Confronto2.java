package settimana2;

import java.util.Scanner;

public class Confronto2 {
    public void controllo(int primoValore, int secondoValore) {
            if (primoValore > secondoValore) {
                System.out.println(primoValore + " è maggiore di " + secondoValore);
            }
            else if(primoValore < secondoValore) {
                System.out.println(secondoValore + " è maggiore di " + primoValore);
            }
    }
    public int leggiEValida(Scanner scanner) {
        int valore = 0;
        boolean flag = false;
        do {
            System.out.println("Inserisci un numero tra 1 e 100:");
            if (scanner.hasNextInt()) {
                valore = scanner.nextInt();
                if (validaNumero(valore)) {
                    flag = true;
                } else {
                    System.out.println("Errore: Il numero deve essere compreso tra 1 e 100.");
                }
            } else {
                System.out.println("Errore: Inserisci un numero intero valido.");
                scanner.next();
            }
        } while (!flag);
        return valore;
    }
    private boolean validaNumero (int valore){
        return valore >= 1 && valore <= 100;
    }
}