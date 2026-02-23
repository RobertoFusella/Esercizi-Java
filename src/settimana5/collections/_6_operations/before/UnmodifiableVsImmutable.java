package settimana5.collections._6_operations.before;

import java.util.HashMap;

public class UnmodifiableVsImmutable
{
    public static void main(String[] args)
    {
        var mutableCountryToPopulation = new HashMap<>();
        mutableCountryToPopulation.put("UK", 67);
        mutableCountryToPopulation.put("USA", 328);
    }
}
