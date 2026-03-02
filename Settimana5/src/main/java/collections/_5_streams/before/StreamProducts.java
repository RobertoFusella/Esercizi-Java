package collections._5_streams.before;

import settimana5.collections._5_streams.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

public class StreamProducts {
    public static void main(String[] args) {
        var door = new Product(1, "Wooden Door", 35);
        var floorPanel = new Product(2, "Floor Panel", 25);
        var window = new Product(3, "Glass Window", 10);

        var products = List.of(door, floorPanel, window, floorPanel, window);

        System.out.println("Esempio Stream");
        System.out.println(namesOfLightProductsWeightSortedStreams(products));

        System.out.println("Esempio Stream con collect");
        var results = products.stream() //trasformiamo la lista in uno Stream
                .filter(product -> product.getWeight() < 30)
                //"filtriamo" solo i prodotti con peso < 30
                .sorted(comparingInt(Product::getWeight))
                //ordiniamo i prodotti in base al peso (dal più leggero al più pesante)
                .collect(Collectors.groupingBy(Product::getName));
                // Raccoglie gli elementi di uno stream e li raggruppa secondo un criterio
                // In questo caso, il criterio è il nome del prodotto
                // Il collector "groupingBy" crea una mappa dove:
                //- La chiave (key) è il valore restituito da Product::getName (cioè il nome del prodotto)
                //- Il valore (value) è una lista di tutti i prodotti che hanno quel nome

        System.out.println("Esempio senza Stream");
        System.out.println(namesOfLightProductsWeightSortedLoop(products));
    }

    // ---- VERSIONE STREAM ----
    private static List<String> namesOfLightProductsWeightSortedStreams(List<Product> products) {

        return products
                .stream() //trasformiamo la lista in uno Stream
                .filter(product -> product.getWeight() < 30)
                //"filtriamo" solo i prodotti con peso < 30
                .sorted(comparingInt(Product::getWeight))
                //ordiniamo i prodotti in base al peso (dal più leggero al più pesante)
                .map(Product::getName)
                //trasformiamo ogni Product nel suo nome
                .distinct()
                //rimuoviamo i duplicati
                .toList();
        //convertiamo il risultato finale in una List immutabile
    }

    // ---- VERSIONE TRADIZIONALE ----
    private static List<String> namesOfLightProductsWeightSortedLoop(List<Product> products) {
        List<Product> lightProducts = new ArrayList<>();

        //filtriamo manualmente i prodotti leggeri
        for (Product product : products) {
            if (product.getWeight() < 30) {
                lightProducts.add(product);
            }
        }

        //ordiniamo manualmente i prodotti filtrati
        lightProducts.sort(comparingInt(Product::getWeight));

        //estraiamo i nomi in una nuova lista
        List<String> productNames = new ArrayList<>();
        for (Product product : lightProducts) {
            productNames.add(product.getName());
        }

        //rendiamo la lista finale immutabile
        return Collections.unmodifiableList(productNames);
    }
}
