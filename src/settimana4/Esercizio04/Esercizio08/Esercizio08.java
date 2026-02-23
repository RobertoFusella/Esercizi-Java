package settimana4.Esercizio04.Esercizio08;

public class Esercizio08 {
    static void main(String[] args) throws InterruptedException {
        AllarmePrioritario allarmePrioritario = new AllarmePrioritario("temperatura troppo alta", 5000);
        allarmePrioritario.accendiAllarme();
        allarmePrioritario.staDormendo();
        Thread.sleep(5000);
        allarmePrioritario.inviaNotifica();
        System.out.println(allarmePrioritario.getPriorita());

        AllarmeAdAltaVisibilita allarmeAdAltaVisibilita = new AllarmeAdAltaVisibilita("temperatura troppo alta");
        allarmeAdAltaVisibilita.accendiAllarme();
        System.out.println(allarmeAdAltaVisibilita.riceviNotifica(true));

        //esempio di polimorfismo, PannelloDiControllo "parla" alla classe allarme e accede
        //anche alle classi figlie(AllarmePrioritorio e AllarmeAdAltaVisibilita) upcasting
        System.out.println("----Esempio polimorfismo----");
        PannelloDiControllo pannelloDiControllo = new PannelloDiControllo();

        pannelloDiControllo.aggiungiAllarme(new AllarmePrioritario("temperatura troppo alta", 4000));
        pannelloDiControllo.aggiungiAllarme(new AllarmeAdAltaVisibilita("temperatura troppo bassa"));
        pannelloDiControllo.aggiungiAllarme(new AllarmeConOrario("temperatura troppo alta"));
        //quando pannelloDiControllo richiama i metodi anche se non sa dell'esistenza della classe Allarme e delle classi figlie,
        //in base all'oggetto andrà a chiamare metodi diversi perchè tutti gli allarmi sono di tipo Allarme ed ereditano da esso
        //AllarmePrioritario eredita riceviNotifica di Allarme e AllarmeAdAltaVisibilita fa un override di riceviNotifica di Allarme
        //quindi quando andremò a chiamare riceviNotifica di AllarmePrioritario anche se non presente lo eredita da Allarme
        //mentre per AllarmeAdAltaVisibilita fa un override di riceviNotifica
        pannelloDiControllo.stampaGrandeMessaggio();

        //esempio downcasting
        //stiamo cercando di chiamare un metodo non presente nella classe padre Allarme Java in questo caso darà errore
        //quindi facciamo una conversione (cast) di Allarme in AllarmePrioritario
        //ATTENZIONE se facciamo male il casting delle classi potrebbe darci l'errore ClassCastException
        Allarme allarmeDwoncasting = new AllarmePrioritario("prova", 2000);

        AllarmePrioritario allarmePrioritarioDowncasting = (AllarmePrioritario) allarmeDwoncasting;
        System.out.println(allarmePrioritarioDowncasting.getPriorita());

        //qui ci dà l'errore ClassCastException
        //per effettuare downcasting più in sicurezza possiamo aggiungere un controllo per vedere se l'istanza
        //di Allarme fa parte di AllarmePrioritario, se è cosi facciamo il downcast e chiamiamo il metodo
        //in questo caso non verrà eseguito niente perchè Allarme è un istanza di AllarmeAdAltaVisibilita
        //ATTENZIONE in generale è sconsigliato usare il downcasting, cerca di non usarlo
        Allarme allarmeDwoncastingProva = new AllarmeAdAltaVisibilita("prova");
        if(allarmeDwoncastingProva instanceof AllarmePrioritario) {
            AllarmePrioritario allarmePrioritarioDowncastingProva = (AllarmePrioritario) allarmeDwoncastingProva;

        System.out.println(allarmePrioritarioDowncastingProva.getPriorita());
        }
        //esempio classe astratta
        //dato che Allarme adesso è astratta e non può più essere istanziata facciamo l'upcasting
        //per usare le classi figlie(che ereditano tutto dalla classe padre Allarme anche se astratta)
        System.out.println("----esempio classe astratta----");
        Allarme allarmeArancione = new AllarmeAdAltaVisibilita("prova con colori");
        System.out.println(allarmeArancione.getColore());
        Allarme allarmeVerde = new AllarmePrioritario("prova con colori",100);
        System.out.println(allarmeVerde.getColore());

        //esempio interfacce
        //le interfacce sono come dei "contratti", se una classe accetta questo contratto deve
        //implementare i metodi che sono presenti dentro le interfacce obbligatoriamente
        //almeno che la classe non è astratta ma le classi figlie devono
        System.out.println("----esempio classe astratta----");
        AllarmeAdAltaVisibilita allarmeInterfaccia = new AllarmeAdAltaVisibilita("prova interfaccia");
        allarmeInterfaccia.accendiAllarme();
        System.out.println(allarmeInterfaccia.getMessaggioDiAiuto());
        allarmeInterfaccia.salva();
    }
}