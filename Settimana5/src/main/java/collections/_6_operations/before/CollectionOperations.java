package collections._6_operations.before;

import settimana5.collections.common.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class CollectionOperations
{
    public static Product door = new Product("Wooden Door", 35);
    public static Product floorPanel = new Product("Floor Panel", 25);
    public static Product window = new Product("Glass Window", 10);

    public static void main(String[] args)
    {
        // Creo una lista di prodotti a partire da tre oggetti (window, floorPanel, door)
        var products = new ArrayList<>(List.of(window, floorPanel, door));

// Stampo la lista così com'è
        System.out.println(products);

// Ruota tutti gli elementi della lista di 1 posizione verso destra
// L'ultimo elemento diventa il primo
        Collections.rotate(products, 1);
        System.out.println(products);

// Mescola gli elementi della lista in modo casuale usando un generatore di numeri casuali
        Collections.shuffle(products, ThreadLocalRandom.current());
        System.out.println(products);

// Creo un elenco di caratteri (alfabeto) tramite un metodo custom makeAlphabet()
        var alphabet = makeAlphabet();
        System.out.println(alphabet);

// Cerca la posizione del carattere 'M' nella lista 'alphabet' usando la ricerca binaria
// ATTENZIONE: Collections.binarySearch richiede che la lista sia **ordinata**
        int index = Collections.binarySearch(alphabet,'M');
        System.out.println(index);
    }

    // Metodo che crea e restituisce una lista di caratteri dall'alfabeto maiuscolo (A-Z)
    private static List<Character> makeAlphabet() {
        return IntStream
                // Crea uno stream di numeri interi da 'A' (65) fino a 'Z' (90) escluso
                // ATTENZIONE: IntStream.range in Java è [start, end) quindi 'Z' non è incluso
                .range('A', 'Z')

                // Converte ogni intero in un oggetto Character
                .mapToObj(x -> (char) x)

                // Raccoglie tutti i caratteri in una lista immutabile
                .toList();
    }
}
