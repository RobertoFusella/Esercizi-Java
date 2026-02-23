package settimana4.Esercizio04.Esercizio06;

public class Esercizio06 {
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
    }
}