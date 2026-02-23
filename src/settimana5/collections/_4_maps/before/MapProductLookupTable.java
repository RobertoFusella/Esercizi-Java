package settimana5.collections._4_maps.before;

import java.util.HashMap;
import java.util.Map;

public class MapProductLookupTable implements ProductLookupTable {
    private static final Map<Integer, Product> idToProduct = new HashMap<>();


    @Override
    public void addProduct(final Product productToAdd) {
        //questo metodo usa .putIfAbsent() controlla se
        //una chiave è già presente in una mappa, se restituisce
        //null la chiave non c'era e inserisce, se non è null
        //la chiave è presente e tira una Exception
        var id = productToAdd.getId();
        if (idToProduct.putIfAbsent(id, productToAdd) != null) {
            throw new RuntimeException("Unable to add product, duplicate id for: " + productToAdd);
        }
    }

    @Override
    public Product lookupById(final int id) {
        return null;
    }

    @Override
    public void clear() {
    }
}
