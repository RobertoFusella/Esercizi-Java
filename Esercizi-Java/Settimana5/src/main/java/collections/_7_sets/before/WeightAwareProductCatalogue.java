package collections._7_sets.before;

import collections.common.Product;
import collections.common.Supplier;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class WeightAwareProductCatalogue implements Iterable<Product>
{
    private final Set<Product> products = new HashSet<>();

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
        return null;
    }
}
