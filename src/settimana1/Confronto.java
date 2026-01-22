package settimana1;

public class Confronto {
    /*
     suggerimenti:
     - è buona norma mettere sempre le parentesi agli if (o for, while, etc) anche se c'è una sola istruzione
     - ricordati di formattare il codice
     */

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
}
