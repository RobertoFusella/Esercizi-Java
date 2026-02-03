void main() {
    //esercizio con array lineari
    double[] valoreSinistro = {70, 40, 300, 33};
    double[] valoreDestro = {45, 120, 42, 6};
    char[] codiceOperazione = {'d', 'a', 's', 'm'};
    double[] risultato = new double[codiceOperazione.length];
    //switch case per ogni operazione
    //d=divisione, a=addizione, s=sottrazione, m=moltiplicazione
    for (int i = 0; i < codiceOperazione.length; i++) {
        switch (codiceOperazione[i]) {
            case 'a':
                risultato[i] = valoreSinistro[i] + valoreDestro[i];
                break;
            case 's':
                risultato[i] = valoreSinistro[i] - valoreDestro[i];
                break;
            case 'm':
                risultato[i] = valoreSinistro[i] * valoreDestro[i];
                break;
            case 'd':
                //controlliamo se il valoreDestro è diverso da zero con un Operatore Ternario
                // in quanto non si può dividere per 0
                //se è vero esegue l'operazione a sinistra dei due punti(:) se è falsa esegue
                //quella a destra (assegna 0 alla posizione [i]
                risultato[i] = valoreDestro[i] != 0 ? valoreSinistro[i] / valoreDestro[i] : 0;
                break;
            default:
                //caso default se si inserisce un codice operazione non corretto
                System.out.println("Codice operazione non valido: " + codiceOperazione[i]);
                risultato[i] = 0;
                break;
        }
        for (double risultatoCorrente : risultato)
            System.out.println(risultatoCorrente);
    }
}


