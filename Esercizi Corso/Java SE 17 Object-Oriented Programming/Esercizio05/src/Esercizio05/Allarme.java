package Esercizio05;

import java.time.LocalDateTime;

public class Allarme {
    private boolean active;
    //rendendo messaggio final stiamo dicendo che non può
    //più essere cambiato una volta assegnato e il suo valore deve essere
    //assegnato sennò il compilatore darà errore
    final String messaggio;
    //LocalDateTime è una classe di java che specifica un momento preciso nel tempo
    LocalDateTime dormiFino;

    public Allarme(String message) {
        this.messaggio = message;
        //chiamiamo il metodo nel costruttore per dire che
        //la sveglia non è attiva
        nonDormendo();
    }
    //metodo che accende l'allarme
    public void accendiAllarme() {
        active = true;
    }
    //metodo che spegne l'allarme
    void spegniAllarme() {
        active = false;
    }
    //questo metodo serve a dire aggiungi 5min rispetto al tempo attuale
    void dormi(){
        dormiFino = LocalDateTime.now().plusMinutes(5);
    }
    //controlla se la variabile dormiFino è nel futuro
    boolean staDormendo(){
        return (dormiFino.isAfter(LocalDateTime.now()));
    }
    //controlla se dormiFino è nel passato di 1sec
    void nonDormendo(){
        dormiFino = LocalDateTime.now().minusSeconds(1);
    }

    //metodo overload di quello originale stampa il messaggio
    //tutto in minuscolo invece di farlo in maiuscolo
    String riceviNotifica() {
        return riceviNotifica(false);
    }
    //prende un valore booleano se true rende tutto il messaggio maiuscolo
    //se false rimane come è
    String riceviNotifica(boolean maiuscola) {
        //controlla se la sveglia è attiva e staDormendo è true
        if (active && !staDormendo()) {
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