package com.monotonic.collections.common;

import java.util.Comparator;

public record Product(String name, int weight) {

    // "record" è un tipo speciale di classe introdotto in Java
    // Serve per rappresentare oggetti immutabili di sola "data"
    // In automatico genera:
    // - costruttore
    // - getter (name() e weight())
    // - equals()
    // - hashCode()
    // - toString() (che qui però viene sovrascritto)

    @Override
    public String toString() {

        return "Product{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    // Comparator statico che permette di confrontare due prodotti
    // in base al loro peso
    public static final Comparator<Product> BY_WEIGHT =

            // comparingInt crea un Comparator che confronta
            // usando un valore int (in questo caso il peso)
            // Product::weight è un method reference
            // equivalente a (product) -> product.weight()
            Comparator.comparingInt(Product::weight);
}