package collections._3_lists.before;

import collections.common.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Definisce la classe Shipment.
//implements Iterable<Product> permette di usare l’oggetto Shipment in un ciclo for-each
public class Shipment implements Iterable<Product> {
    // Costante che rappresenta il valore restituito da indexOf()
    // quando un prodotto non viene trovato nella lista
    private static final int MISSING_PRODUCT = -1;

    // Peso massimo consentito per il furgone leggero
    // I prodotti con peso maggiore andranno nel furgone pesante
    private static final int LIGHT_VAN_MAX_WEIGHT = 20;

    // Lista principale che contiene tutti i prodotti della spedizione.
    // È final perché il riferimento alla lista non cambia,
    // ma il contenuto può essere modificato (aggiunte, rimozioni, ecc.)
    private final List<Product> products = new ArrayList<>();

    // Lista dei prodotti destinati al furgone leggero
    // Viene inizializzata nel metodo prepare()
    private List<Product> lightVanProducts;

    // Lista dei prodotti destinati al furgone pesante
    // Viene inizializzata nel metodo prepare()
    private List<Product> heavyVanProducts;

    // Metodo per aggiungere un prodotto alla spedizione
    public void add(Product product) {
        // Aggiunge il prodotto alla lista principale
        products.add(product);
    }

    // Metodo che sostituisce un prodotto esistente con uno nuovo
    // Restituisce true se la sostituzione è avvenuta, false altrimenti
    public boolean replace(Product oldProduct, Product newProduct) {
        // Cerca la posizione del prodotto da sostituire
        int position = products.indexOf(oldProduct);

        // Se il prodotto non è presente nella lista
        if (position == MISSING_PRODUCT) {
            return false;
        } else {
            // Sostituisce il prodotto alla posizione trovata
            products.set(position, newProduct);
            return true;
        }
    }

    // Metodo che prepara la spedizione dividendo i prodotti
    // tra furgone leggero e furgone pesante
    public void prepare() {
        // Ordina i prodotti in base al peso (dal più leggero al più pesante)
        // Product.BY_WEIGHT è un Comparator definito nella classe Product
        products.sort(Product.BY_WEIGHT);

        // Trova l'indice del primo prodotto che supera il peso massimo
        int splitPoint = findSplitPoint();

        // Crea una sottolista dei prodotti leggeri (peso <= 20)
        // subList non crea una nuova lista indipendente,
        // ma una vista sulla lista originale
        lightVanProducts = products.subList(0, splitPoint);

        // Crea una sottolista dei prodotti pesanti (peso > 20)
        heavyVanProducts = products.subList(splitPoint, products.size());
    }

    // Metodo privato che individua il punto di divisione
    // tra prodotti leggeri e pesanti
    private int findSplitPoint() {
        int size = products.size();

        // Scorre tutti i prodotti
        for (int i = 0; i < size; i++) {

            // Recupera il prodotto alla posizione corrente
            var product = products.get(i);

            // Se il peso supera il limite del furgone leggero
            if (product.weight() > LIGHT_VAN_MAX_WEIGHT) {
                // Restituisce l'indice come punto di divisione
                return i;
            }
        }

        // Se nessun prodotto supera il peso massimo,
        // significa che tutti possono andare nel furgone leggero
        return products.size();
    }

    // Restituisce la lista dei prodotti destinati al furgone pesante
    public List<Product> getHeavyVanProducts() {
        return heavyVanProducts;
    }

    // Restituisce la lista dei prodotti destinati al furgone leggero
    public List<Product> getLightVanProducts() {
        return lightVanProducts;
    }

    // Metodo richiesto dall'interfaccia Iterable<Product>
    // Permette di usare l'oggetto Shipment in un ciclo for-each
    @Override
    public Iterator<Product> iterator() {
        // Restituisce l'iteratore della lista principale
        return products.iterator();
    }

    // Rimuove dalla lista principale tutti i prodotti
    // che superano il peso massimo del furgone leggero
    public boolean stripHeavyProducts() {
        // removeIf accetta una lambda:
        // rimuove ogni prodotto con peso > 20
        // restituisce true se almeno un elemento è stato rimosso
        return products.removeIf(product -> product.weight() > LIGHT_VAN_MAX_WEIGHT);
    }
}