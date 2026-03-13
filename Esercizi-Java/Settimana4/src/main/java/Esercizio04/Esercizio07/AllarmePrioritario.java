package Esercizio04.Esercizio07;

//classe figlia che estende Allarme, eredita tutti i suoi attributi e metodi
public class AllarmePrioritario extends Allarme {
    private final int priorita;

    //in questo costruttore avevamo un problema derivato dal fatto che mancava un costruttore vuoto
    //alla classe padre. Usando super() stiamo chiamando il costruttore della classe
    //padre e gli stiamo passando una stringa come parametro, cosi facendo quando questo costruttore
    //viene usato chiamerà in automatico il costruttore presente nella classe padre
    public AllarmePrioritario(String messaggio, int priorita) {
        super(messaggio);
        this.priorita = priorita;
    }

    public int getPriorita() {
        return priorita;
    }
}
