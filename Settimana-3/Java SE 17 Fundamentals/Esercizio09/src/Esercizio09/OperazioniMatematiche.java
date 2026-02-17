package Esercizio09;

public class OperazioniMatematiche {
    private double valoreSinistro;
    private double ValoreDestro;
    private char codiceOperazione;
    private double risultato;
    //costruttore di default (fornito di base da java quando non ne abbiamo uno)
    public OperazioniMatematiche(){}
    //costruttore che inizializza solo il codiceOperazione
    public OperazioniMatematiche(char codiceOperazione){
        this.codiceOperazione= codiceOperazione;
    }
    //costruttore che inizializza tutti gli attributi
    //this funge da riferimento all'istanza corrente dell'oggetto, è proprio quel riferimento
    //in pratica indica l'istanza corrente della classe (è come dire questo codiceOperazione mettilo proprio all'interno
    //dell'attributo della classe in questo caso sempre codiceOperazione)
    public OperazioniMatematiche(char codiceOperazione, double valoreSinistro, double ValoreDestro){
        this.codiceOperazione= codiceOperazione;
        this.valoreSinistro = valoreSinistro;
        this.ValoreDestro = ValoreDestro;
    }

    //metodo uguale a quello presente nel main solo che qui lo inseriamo
    //all'interno di una classe
    void eseguiOperazioni() {
        switch (codiceOperazione) {
            case 'a':
                risultato = valoreSinistro + ValoreDestro;
                break;
            case 's':
                risultato = valoreSinistro - ValoreDestro;
                break;
            case 'm':
                risultato = valoreSinistro * ValoreDestro;
                break;
            case 'd':
                risultato = ValoreDestro != 0 ? valoreSinistro / ValoreDestro : 0;
                break;
            default:
                System.out.println("codice operazione non valido: " + codiceOperazione);
                risultato = 0;
                break;
        }

    }
    //metodi get e set per incapsulare gli attributi della classe
    public double getValoreSinistro() {
        return valoreSinistro;
    }
    //this funge da riferimento all'istanza corrente dell'oggetto, è proprio quel riferimento
    //in pratica indica l'istanza corrente della classe (è come dire questo valoreSinistro mettilo proprio all'interno
    //dell'attributo della classe in questo caso sempre valoreSinistro)
    public void setValoreSinistro(double valoreSinistro) {
        this.valoreSinistro = valoreSinistro;
    }

    public double getValoreDestro() {
        return ValoreDestro;
    }

    public void setValoreDestro(double valoreDestro) {
        this.ValoreDestro = valoreDestro;
    }

    public char getCodiceOperazione() {
        return codiceOperazione;
    }

    public void setCodiceOperazione(char codiceOperazione) {
        this.codiceOperazione = codiceOperazione;
    }

    public double getRisultato() {
        return risultato;
    }

    public void setRisultato(double risultato) {this.risultato = risultato;}

}
