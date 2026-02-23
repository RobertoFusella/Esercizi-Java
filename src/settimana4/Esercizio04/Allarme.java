package settimana4.Esercizio04;

public class Allarme {
    private boolean active;
    //rendendo messaggio final stiamo dicendo che non può
    //più essere cambiato una volta assegnato e il suo valore deve essere
    //assegnato sennò il compilatore darà errore
    final String messaggio;

    public Allarme(String message) {
        this.messaggio = message;
    }
    //metodo che accende l'allarme
    public void accendiAllarme() {
        active = true;
    }
    //metodo che spegne l'allarme
    void spegniAllarme() {
        active = false;
    }
    //metodo overload di quello originale stampa il messaggio
    //tutto in minuscolo invece di farlo in maiuscolo
    String riceviNotifica() {
        return riceviNotifica(false);
    }
    //prende un valore booleano se true rende tutto il messaggio maiuscolo
    //se false rimane come è
    String riceviNotifica(boolean maiuscola) {
        if (active) {
            if (maiuscola) {
                return messaggio.toUpperCase();
            } else {
                return messaggio;
            }
        } else {
            return "";
        }
    }
    //stampa il messaggio e decide se farlo in maiuscolo o minuscolo
    public void inviaNotifica() {
        System.out.println(riceviNotifica(true));
    }
}