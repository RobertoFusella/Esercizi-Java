package Esercizio08;

public class OperazioniMatematiche {
    private double valoreSinistro;
    private double ValoreDestro;
    private char codiceOperazione;
    private double risultato;
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

    public void setRisultato(double risultato) {
        this.risultato = risultato;
    }

}