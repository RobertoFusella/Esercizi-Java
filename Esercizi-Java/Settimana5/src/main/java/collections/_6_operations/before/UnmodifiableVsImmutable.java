package collections._6_operations.before;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnmodifiableVsImmutable {
    public static void main(String[] args) {
        var mutableCountryToPopulation = new HashMap<>();
        mutableCountryToPopulation.put("UK", 67);
        mutableCountryToPopulation.put("USA", 328);

        var unmodifiable = Collections.unmodifiableMap(mutableCountryToPopulation);

        var copied = Map.copyOf(mutableCountryToPopulation);
        System.out.println("prima del put");
        System.out.println("mutableCountryToPopulation = " + mutableCountryToPopulation);
        System.out.println("unmodifiable = " + unmodifiable);
        System.out.println("copied = " + copied);

        System.out.println("dopo put");
        mutableCountryToPopulation.put("Germany", 83);
        System.out.println("mutableCountryToPopulation = " + mutableCountryToPopulation);
        System.out.println("unmodifiable = " + unmodifiable);
        System.out.println("copied = " + copied);
    }
}
