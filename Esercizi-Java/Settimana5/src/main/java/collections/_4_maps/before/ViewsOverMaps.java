package collections._4_maps.before;

import java.util.HashMap;
import java.util.Map;

public class ViewsOverMaps
{
    public static void main(String[] args)
    {
        var idToProduct = new HashMap<Integer, Product>();
        idToProduct.put(1, ProductFixtures.door);
        idToProduct.put(2, ProductFixtures.floorPanel);
        idToProduct.put(3, ProductFixtures.window);

        //.keySet restituisce una view delle chiavi della mappa
        //se la mappa viene modificata anche la view si modifica
        //non possiamo aggiungere niente in quanto una view
        var ids = idToProduct.keySet();
        System.out.println(ids);
        System.out.println(idToProduct);

        System.out.println("---------------");

        //.values restituisce una view della collezione di Product
        //si comporta come .keySet ma per i valori. Anche qui se
        //modifichiamo la mappa si modifica la view
        //non possiamo aggiungere niente in quanto una view
        var products = idToProduct.values();
        System.out.println(products);
        System.out.println(idToProduct);

        System.out.println("---------------");
        //view usando entrySet, anche qui se modifichiamo la mappa
        //anche la view cambia, non possiamo aggiungere niente in quanto una view
        var entries = idToProduct.entrySet();
        System.out.println(entries);

        //con il metodo .entry presente in Map possiamo creare la nostra
        //"entrata" personalizzata in una mappa
        var entry = Map.entry(1, ProductFixtures.door);

        entries.remove(entry);
        System.out.println(entries);
        System.out.println(idToProduct);

//        idToProduct.forEach((k,v) ->
//        {
//        System.out.println(k);
//        System.out.println(v);
//        });
    }
}
