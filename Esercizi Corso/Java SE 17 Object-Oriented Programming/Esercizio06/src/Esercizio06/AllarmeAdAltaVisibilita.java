package Esercizio06;

public class AllarmeAdAltaVisibilita extends Allarme {
    public AllarmeAdAltaVisibilita(String messaggio) {
        super(messaggio);
    }

    @Override
    public String riceviNotifica(boolean maiuscola) {
        //super si usa anche per richiamare metodi della classe padre
        String messaggio = super.riceviNotifica(maiuscola);
        if (messaggio.isEmpty()) {
            return messaggio;
        } else {
            return messaggio + "!";
        }
    }
}
