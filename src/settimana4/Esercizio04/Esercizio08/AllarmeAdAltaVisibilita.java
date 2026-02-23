package settimana4.Esercizio04.Esercizio08;

import java.awt.*;

public class AllarmeAdAltaVisibilita extends Allarme {

    public AllarmeAdAltaVisibilita(String messaggio) {
        super(messaggio);
    }

    //override del metodo presente nella classe padre
    @Override
    public Color getColore() {
        return Color.ORANGE;
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
