package collections._4_maps.before;

import java.util.HashMap;
import java.util.Map;

public class NaiveProductLookupTable implements ProductLookupTable
{
    // Mappa che associa un ID (Integer) a un oggetto Product.
// - La chiave è l'ID del prodotto
// - Il valore è il prodotto stesso
// HashMap permette accesso veloce (O(1) in media) tramite chiave.
// final significa che il riferimento alla mappa non può cambiare,
// ma il contenuto può essere modificato (put, clear, ecc.)
    private final Map<Integer, Product> idToproduct = new HashMap<>();

    @Override
    public void addProduct(final Product productToAdd)
    {
        // Recupera l'ID del prodotto da aggiungere
        var id = productToAdd.getId();

        // Controlla se nella mappa esiste già un prodotto con lo stesso ID
        // Questo evita duplicati, perché in questa struttura
        // ogni ID deve essere univoco
        if(idToproduct.containsKey(id)){
            // Se l'ID è già presente, lancia un'eccezione
            // IllegalArgumentException indica che l'argomento passato
            // al metodo non è valido (in questo caso ID duplicato)
            throw new IllegalArgumentException(
                    "Unable to add product duplicate id for " + productToAdd
            );
        }

        // Se l'ID non è presente, inserisce il prodotto nella mappa
        // put associa la chiave (id) al valore (productToAdd)
        idToproduct.put(id, productToAdd);
    }

    @Override
    public Product lookupById(final int id)
    {
        // Restituisce il prodotto associato all'ID passato.
        // Se l'ID non esiste nella mappa, get() restituisce null.
        return idToproduct.get(id);
    }

    @Override
    public void clear()
    {
        // Rimuove tutte le associazioni dalla mappa.
        // Dopo questa chiamata la mappa sarà vuota.
        idToproduct.clear();
    }
}
