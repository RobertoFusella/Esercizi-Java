package Esercizio06;

public class Main {
    public static void main(String[] args) {
        //esercizio con array lineari
        double[] valoreSinistro = {70, 40, 300, 33};
        double[] valoreDestro = {45, 120, 42, 6};
        char[] codiceOperazione = {'d', 'a', 's', 'm'};
        double[] risultato = new double[codiceOperazione.length];
        //controlliamo se la lunghezza di args è uguale a 0
        //se uguale a 0 eseguiamo le operazioni con l'array codiceOperaiozne
        if (args.length == 0) {
            for (int i = 0; i < codiceOperazione.length; i++) {
                risultato[i] = eseguiCalcoli(codiceOperazione[i], valoreSinistro[i], valoreDestro[i]);
            }
            for (double currentResult : risultato) {
                System.out.println(currentResult);
            }
            //se la lunghezza di args è uguale a 3 chiamiamo il metodo gestisciComandi
        } else if (args.length == 3){
            gestisciComandi(args);
        } else {
            System.out.println("per favore inserire una operazione e 2 numeri");
        }
    }

    //Prendiamo la prima lettera fornita come codice operazione
    //es. Moltiplicazione andremo a prendere solo la M
    //ed eseguiamo il parse in double in quanto stiamo passando i numeri come Stringa
    private static void gestisciComandi(String[] args) {
        char codiceOperazione = args[0].charAt(0);
        double valoreSinistro = Double.parseDouble(args[1]);
        double valoreDestro = Double.parseDouble(args[2]);
        double risultato = eseguiCalcoli(codiceOperazione, valoreSinistro, valoreDestro);
        System.out.println(risultato);
    }

    static double eseguiCalcoli(char codiceOperazione, double valoreSinistro, double valoreDestro) {
        double risultato;
        switch (codiceOperazione) {
            case 'a':
                risultato = valoreSinistro + valoreDestro;
                break;
            case 's':
                risultato = valoreSinistro - valoreDestro;
                break;
            case 'm':
                risultato = valoreSinistro * valoreDestro;
                break;
            case 'd':
                risultato = valoreDestro != 0 ? valoreSinistro / valoreDestro : 0.0d;
                break;
            default:
                System.out.println("codice operazione non valido: " + codiceOperazione);
                risultato = 0;
                break;
        }
        return risultato;
    }
}

