/*Scrivi un semplice programma Java che:
legga un valore
esegua un controllo
stampi un risultato
Spiega un errore di compilazione che hai incontrato questa settimana
*/
void main() {
    Scanner scanner = new Scanner(System.in);
    try {
    System.out.println("inserisci primo valore");
    int valore1 = scanner.nextInt();
    System.out.println("inserisci secondo valore");
    int valore2 = scanner.nextInt();


        if (valore1 > valore2)
            System.out.println(valore1 + " è maggiore di " + valore2);
        else if (valore2 > valore1)
            System.out.println(valore2 + " è maggiore di " + valore1);
        else if (valore1 == valore2)
            System.out.println("i numeri sono uguali");
        //un errore che ho incontrato su questo esercizio era l'input
        //se inserivo qualcosa di diverso da un intero (es. un char)
        //mi dava come errore: InputMismatchException
    } catch (InputMismatchException e) {
        System.out.println("inserisci un valore valido");
    }
    System.out.println("nuova funzione");
}
