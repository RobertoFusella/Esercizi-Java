package Esercizio08;

import java.time.LocalDateTime;
import java.awt.Color;
public abstract class Allarme implements OggettoPersistente, Widged {
    //rendendo l'attributo protected lo stiamo rendendo visibile anche per le classi figlie
    //ma anche visibile nello stesso pacchetto
    protected boolean accesso;
    //rendendo messaggio final stiamo dicendo che non può
    //più essere cambiato una volta assegnato e il suo valore deve essere
    //assegnato sennò il compilatore darà errore
    private final String messaggio;
    //LocalDateTime è una classe di java che specifica un momento preciso nel tempo
    private LocalDateTime dormiFino;

    public Allarme(String message) {
        this.messaggio = message;
        //chiamiamo il metodo nel costruttore per dire che
        //la sveglia non è attiva
        nonDormendo();
    }
    //metodi dichiarati nelle interfacce e implementati nella classe padre
    @Override
    public String getMessaggioDiAiuto() {
        return "sono una sveglia. puoi accendermi o spegnermi e posticiparmi";
    }
    //metodi dichiarati nelle interfacce e implementati nella classe padre
    @Override
    public void salva() {
        System.out.println("Salvando...");
    }

    //metodo per aggiungere colori agli allarmi
    //vogliamo AllarmeAdAltaVisibilita arancione e AllarmePrioritario verde
    //ma Allarme non ha un colore quindi dobbiamo rendere la classe astratta
    //rendendola astratta non possiamo più istanziare un Allarme ma solo le classi figlie(upcasting)
    public Color getColore(){
        return null;
    }

    public boolean isAccesso() {
        return accesso;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public LocalDateTime getDormiFino() {
        return dormiFino;
    }

    public void setDormiFino(LocalDateTime dormiFino) {
        this.dormiFino = dormiFino;
    }

    //metodo che accende l'allarme
    public void accendiAllarme() {
        accesso = true;
        nonDormendo();
    }

    //metodo che spegne l'allarme
    public void spegniAllarme() {
        accesso = false;
        nonDormendo();
    }

    //questo metodo serve a dire aggiungi 5min rispetto al tempo attuale
    public void dormi() {
        if (accesso) {
            dormiFino = LocalDateTime.now().plusMinutes(5);
        }
    }

    //controlla se la variabile dormiFino è nel futuro
    public boolean staDormendo() {
        return (dormiFino.isAfter(LocalDateTime.now()));
    }

    //controlla se dormiFino è nel passato di 1sec
    private void nonDormendo() {
        dormiFino = LocalDateTime.now().minusSeconds(1);
    }

    //metodo overload di quello originale stampa il messaggio
    //tutto in minuscolo invece di farlo in maiuscolo
    public String riceviNotifica() {
        return riceviNotifica(false);
    }

    //prende un valore booleano se true rende tutto il messaggio maiuscolo
    //se false rimane come è
    public String riceviNotifica(boolean maiuscola) {
        //controlla se la sveglia è attiva e staDormendo è true
        if (accesso && !staDormendo()) {
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