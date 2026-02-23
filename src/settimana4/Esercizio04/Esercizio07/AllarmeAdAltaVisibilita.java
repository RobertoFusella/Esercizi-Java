package settimana4.Esercizio04.Esercizio07;

public class AllarmeAdAltaVisibilita extends Allarme {

    public AllarmeAdAltaVisibilita(String messaggio) {
        super(messaggio);
    }

    @Override
    public String riceviNotifica(boolean maiuscola) {
        //super si usa anche per richiamare metodi della classe padre
        //in questo caso andrà a sovrascrivere il metodo originale (override)
        String messaggio = super.riceviNotifica(maiuscola);
        if (messaggio.isEmpty()) {
            return messaggio;
        } else {
            return messaggio + "!";
        }
    }
}
