package Esercizio04.Esercizio08;

import java.time.LocalTime;

public class AllarmeConOrario extends Allarme {

    public AllarmeConOrario(String messaggio) {
        super(messaggio);
    }
    @Override
    public String riceviNotifica(boolean maiuscola){
    String messaggio = super.riceviNotifica(maiuscola);
    if(messaggio.isEmpty()){
        return messaggio;
        }else{
        return LocalTime.now() + ": " + messaggio;
        }
    }
}
