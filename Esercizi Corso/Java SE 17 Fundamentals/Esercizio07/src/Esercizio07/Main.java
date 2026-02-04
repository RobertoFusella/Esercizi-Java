package Esercizio07;

import java.util.Scanner;

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
            for (double risultatoCorrente : risultato) {
                System.out.println(risultatoCorrente);
            }
            //se la lunghezza di args è uguale a 1 e nelle impostazioni di esecuzione inseriamo "interrativo"
            // chiamiamo il metodo eseguiInterrativo
        } else if (args.length == 1 && args[0].equals("interrativo")) {
            eseguiInterrativo();
            //se la lunghezza di args è uguale a 3 chiamiamo il metodo gestisciComandi
        } else if (args.length == 3) {
            gestisciComandi(args);
        } else {
            System.out.println("per favore inserire una operazione e 2 numeri");
        }
    }
    //prende in input una stringa e la divide in 3 parti usando .split(qui mettiamo uno spazio)
    //e chiama il metodo eseguiOperazioni su queste 3 parti
    static void eseguiInterrativo() {
        System.out.println("inserisci una operazione e 2 numeri");
        Scanner scanner = new Scanner(System.in);
        String inputUtente = scanner.nextLine();
        String[] parti = inputUtente.split(" ");
        eseguiOperazioni(parti);
    }
    //metodo per assegnare a ogni parte di stringa il suo valore
    //es. all'indice 0 chiamiamo il metodo codiceOperazioneToString
    // e alle altre 2 parti(indice 1 e 2) il metodo valoreDaParola
    private static void eseguiOperazioni(String[] parte) {
        char codiceOperazione = codiceOperazioneToString(parte[0]);
        double valoreSinistro = valoreDaParola(parte[1]);
        double valoreDestro = valoreDaParola(parte[2]);
        double risultato = eseguiCalcoli(codiceOperazione, valoreSinistro, valoreDestro);
        mostraRisultato(codiceOperazione, valoreSinistro, valoreDestro, risultato);
    }

    //metodo per mostrare il risultato usando lo StringBuilder
    //si crea una variabile SringBuilder e con .append aggiungiamo le Stringe al buffer
    private static void mostraRisultato(char codiceOperazione, double valoreSinistro, double valoreDestro, double risultato) {
        char simbolo = simboloDaCodiceOperazione(codiceOperazione);
        StringBuilder builder = new StringBuilder(20);
        builder.append(valoreSinistro);
        builder.append(" ");
        builder.append(simbolo);
        builder.append(" ");
        builder.append(valoreDestro);
        builder.append(" = ");
        builder.append(risultato);
        String output = builder.toString();
        System.out.println(output);
    }

    //prende in input un codiceOperazione e lo associa al suo simbolo
    // (es. se scrivo a(addizione) lo associa al simbolo +)
    private static char simboloDaCodiceOperazione(char codiceOperazione) {
        char[] codiciOperazione = {'a', 's', 'm', 'd'};
        char[] simboli = {'+', '-', '*', '/'};
        char simbolo = ' ';
        for (int i = 0; i < codiciOperazione.length; i++) {
            if (codiceOperazione == codiciOperazione[i]) {
                simbolo = simboli[i];
                break;
            }
        }

        return simbolo;
    }
    //Prendiamo la prima lettera fornita(dagli argometi forniti nel menu di esecuzione) come codice operazione usando .charAt()
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
    //prende la prima lettera di una stringa e la usa come codiceOperazione
    // es. multiplicazione prende solo la M
    static char codiceOperazioneToString(String nomeOperazione) {
        char codiceOperazione = nomeOperazione.charAt(0);
        return codiceOperazione;
    }

    //prende una parola in input e controlla se è uguale alle parole presenti dentro l'array
    //se sono uguale restituisce l'indice della parola (es. se scrivo tre dato che si trova alla posizione
    // 3 partendo da zero restituisce 3)
    static double valoreDaParola(String parola) {
        String[] numeroParola = {
                "zero", "uno", "due", "tre", "quattro", "cinque", "sei", "sette", "otto", "nove"
        };
        double valore = 0;
        for (int i = 0; i < numeroParola.length; i++) {
            if (parola.equals(numeroParola[i])) {
                valore = i;
                break;
            }
        }
        return valore;
    }
}