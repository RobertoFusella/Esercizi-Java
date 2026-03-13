package collections._4_maps.before;

import java.util.HashMap;

public class AdvancedOperations {
    public static void main(String[] args) {
        Product defaultProduct = new Product(-1, "Whatever the customer wants", 100);

        HashMap<Integer, Product> idToProduct = new HashMap<Integer, Product>();
        idToProduct.put(1, ProductFixtures.door);
        idToProduct.put(2, ProductFixtures.floorPanel);
        idToProduct.put(3, ProductFixtures.window);

        //getOrDefault() cerca la chiave specificata, se non la trova
        //restituisce un default
        System.out.println("Esempio getOrDefault");
        System.out.println(idToProduct.getOrDefault(10, defaultProduct));

        System.out.println("----------");

        System.out.println("Esempio replaceAll");
        System.out.println(idToProduct);
        idToProduct.replaceAll((key, oldProduct) -> {
            // 'key' è la chiave corrente della mappa (Integer)
            // 'oldProduct' è il valore corrente della mappa (Product)
            return new Product(
                    oldProduct.getId(),        // Manteniamo lo stesso ID del prodotto
                    oldProduct.getName(),      // Manteniamo lo stesso nome
                    oldProduct.getWeight() + 10 // Incrementiamo il peso di 10
            );
            // Il nuovo Product restituito sostituirà 'oldProduct' nella mappa
        });
        System.out.println(idToProduct);

        System.out.println("----------");

        System.out.println("Esempio computeIfAbsent");
        var result = idToProduct.computeIfAbsent(10, key ->
                        // La lambda viene chiamata solo se la chiave 10 NON esiste nella mappa
                        // 'key' è la chiave mancante (qui 10)
                        new Product(key, "Unknow Product", 100)
                // Crea un nuovo Product con:
                // id = key (10)
                // name = "Unknow Product"
                // weight = 100
        );
        System.out.println(result);
        // Stampa il prodotto risultante:
        // - se la chiave 10 era già presente → stampa il prodotto esistente
        // - se la chiave 10 non c’era → stampa il nuovo Product appena creato
    }
}
