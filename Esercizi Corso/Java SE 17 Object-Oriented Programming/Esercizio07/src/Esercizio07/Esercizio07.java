package Esercizio07;

public class Esercizio07 {
    static void main(String[] args) throws InterruptedException {
        Allarme allarme = new Allarme("temperatura troppo alta");
        allarme.accendiAllarme();
        allarme.staDormendo();
        Thread.sleep(5000);
        allarme.inviaNotifica();

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
        //anche alle classi figlie(AllarmePrioritorio e AllarmeAdAltaVisibilita)
        System.out.println("----Esempio polimorfismo----");
        PannelloDiControllo pannelloDiControllo = new PannelloDiControllo();

        pannelloDiControllo.aggiungiAllarme(new AllarmePrioritario("temperatura troppo alta", 4000));
        pannelloDiControllo.aggiungiAllarme(new AllarmeAdAltaVisibilita("temperatura troppo alta"));
        pannelloDiControllo.aggiungiAllarme(new AllarmeConOrario("temperatura troppo alta"));
        //quando pannelloDiControllo richiama i metodi, in base all'oggetto andrà chiamerà metodi diversi
        //AllarmePrioritario eredita riceviNotifica e AllarmeAdAltaVisibilita fa un override di riceviNotifica
        pannelloDiControllo.stampaGrandeMessaggio();
    }
}