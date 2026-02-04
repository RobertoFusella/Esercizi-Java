package Esercizio08;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //controlliamo se la lunghezza di args è uguale a 0
        //se uguale a 0 eseguiamo le operazioni con l'array codiceOperaiozne
        if (args.length == 0) {
            eseguiCalcoli();
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

    static void eseguiCalcoli() {
        //istanziamo un array di tipo OperazioniMatematiche (dimensione 4)
        OperazioniMatematiche[] equazioni = new OperazioniMatematiche[4];
        //per ogni istanza chiamiamo il metodo riempiOggetto
        equazioni[0] = riempiOggetto(70, 45, 'd');
        equazioni[1] = riempiOggetto(40, 120, 'a');
        equazioni[2] = riempiOggetto(300, 42, 's');
        equazioni[3] = riempiOggetto(33, 6, 'm');
        //ciclo for each cicla per ogni istanza di equazioni (in questo caso 4) e legge i valori
        //e chiama per ogni istanza il metodo eseguiOperazioni presente dentro la classe OperazioniMatematiche
        for (OperazioniMatematiche equazione : equazioni) {
            equazione.eseguiOperazioni();
            System.out.println("risultato = " + equazione.getRisultato());
        }
    }

    //metodo per inizializzare un oggetto di OperazioniMatematiche usando i metodi get e set
    //prendendo i parametri di input del metodo e passandoli agli attributi
    private static OperazioniMatematiche riempiOggetto(double valoreSinistro, double valoreDestro, char codiceOperazione) {
        OperazioniMatematiche equazione = new OperazioniMatematiche();
        equazione.setValoreSinistro(valoreSinistro);
        equazione.setValoreDestro(valoreDestro);
        equazione.setCodiceOperazione(codiceOperazione);
        return equazione;
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
    private static void eseguiOperazioni(String[] parti) {
        char codiceOperazione = codiceOperazioneToString(parti[0]);
        double valoreSinistro = valoreDaParola(parti[1]);
        double valoreDes = valoreDaParola(parti[2]);
        double risultato = eseguiCalcoli(codiceOperazione, valoreSinistro, valoreDes);
        mostraRisultato(codiceOperazione, valoreSinistro, valoreDes, risultato);
    }

    //metodo per mostrare il risultato usando lo StringBuilder
    //si crea una variabile SringBuilder e con .append aggiungiamo le Stringe al buffer
    //infine "montiamo" la stringa con toString
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
        for (int index = 0; index < codiciOperazione.length; index++) {
            if (codiceOperazione == codiciOperazione[index]) {
                simbolo = simboli[index];
                break;
            }
        }
        return simbolo;
    }

    //Prendiamo la prima lettera fornita come codice operazione usando .charAt()
    //es. Moltiplicazione andremo a prendere solo la M
    //ed eseguiamo il parse in double in quanto stiamo passando i numeri come Stringa
    private static void gestisciComandi(String[] args) {
        char codiceOperazione = args[0].charAt(0);
        double valoreSinistro = Double.parseDouble(args[1]);
        double valoreDestro = Double.parseDouble(args[2]);
        double risultato = eseguiCalcoli(codiceOperazione, valoreSinistro, valoreDestro);
        System.out.println(risultato);
    }
    //metodo presente anche dentro la classe ma questo lo passiamo al metodo gestisciComandi
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
    // es. multiplicazione prende solo la M usando .charAt()
    static char codiceOperazioneToString(String nomeOperazione) {
        return nomeOperazione.charAt(0);
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