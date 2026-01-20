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
    Confronto confronto = new Confronto();
    confronto.controllo(valore1,valore2);
        //un errore che ho incontrato su questo esercizio era l'input
        //se inserivo qualcosa di diverso da un intero (es. un char)
        //mi dava come errore: InputMismatchException
    } catch (InputMismatchException e) {
        System.out.println("valore non valido");
    }
}
