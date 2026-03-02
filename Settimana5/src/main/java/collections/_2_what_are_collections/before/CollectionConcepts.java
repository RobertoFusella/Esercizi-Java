package collections._2_what_are_collections.before;

import settimana5.collections.common.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionConcepts
{
    public static void main(String[] args)
    {
        // Creazione di alcuni oggetti Product
        var door = new Product("Wooden Door", 35);
        var floorPanel = new Product("Floor Panel", 25);
        var window = new Product("Glass Window", 10);

        // ----------------------------
        // Collection<Product> products
        // ----------------------------
        // Collection è un'interfaccia di alto livello che rappresenta
        // un insieme di elementi. Non permette accesso diretto tramite indice.
        // ArrayList è una classe concreta che implementa Collection (e List).
        // Qui stiamo usando Collection come tipo generale, ma l'oggetto
        // concreto è un ArrayList.
        Collection<Product> products = new ArrayList<>();

        // Aggiunta degli oggetti alla Collection
        products.add(door);
        products.add(floorPanel);
        products.add(window);

        System.out.println("----esempio di alcuni metodi delle Collection----");
        System.out.println(products.size());      // Restituisce il numero di elementi nella Collection
        System.out.println(products.isEmpty());   // Controlla se la Collection è vuota
        System.out.println(products.contains(door)); // Controlla se un oggetto è presente nella Collection

        // ----------------------------
        // Esempio di for-each
        // ----------------------------
        // Il for-each è un modo semplice per iterare su tutti gli elementi
        // di una Collection senza dover gestire manualmente un Iterator.
        System.out.println("----esempio for each----");
        for(var p : products){
            System.out.println(p);  // Stampa ogni Product presente nella Collection
        }

        // ----------------------------
        // Esempio di Iterator
        // ----------------------------
        // Un Iterator permette di scorrere gli elementi di una Collection
        // e rimuovere elementi in modo sicuro durante l'iterazione.
        System.out.println("----esempio di Iterator----");
        Iterator<Product> it = products.iterator(); // Otteniamo un Iterator sulla Collection

        while(it.hasNext()){   // hasNext() restituisce true se ci sono ancora elementi
            var product = it.next(); // next() restituisce il prossimo elemento
            if (product.weight() > 20){ // Controllo sul peso
                it.remove();   // Rimuove l'elemento corrente dalla Collection in modo sicuro
            }
        }
        System.out.println(products); // Mostra la Collection dopo le rimozioni

        // ----------------------------
        // Esempio di ArrayList e removeAll
        // ----------------------------
        // ArrayList è una List concreta che permette accesso tramite indice,
        // aggiunte, rimozioni e contiene una gestione ordinata degli elementi.
        System.out.println("----esempio di ArrayList----");
        var toRemove = new ArrayList<Product>(); // Creiamo un'altra ArrayList
        toRemove.add(door);
        toRemove.add(floorPanel);

        // removeAll rimuove dalla Collection tutti gli elementi presenti in toRemove
        products.removeAll(toRemove);
        System.out.println(products); // Mostra la Collection finale
    }
}