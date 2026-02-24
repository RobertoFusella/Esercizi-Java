package settimana5.collections._7_sets.before;

import settimana5.collections.common.Product;
import settimana5.collections.common.Supplier;

import java.util.*;

public class ProductCatalogue implements Iterable<Product>
{
    private final NavigableSet<Product> products = new TreeSet<>(Product.BY_WEIGHT);
    public void addSupplier(final Supplier supplier)
    {
        products.addAll(supplier.getProducts());
    }

    @Override
    public Iterator<Product> iterator()
    {
        return products.iterator();
    }

    public Set<Product> findLighterProducts(final Product product)
    {
        return products.headSet(product);
    }
}
