package com.monotonic.collections._6_operations.before;

import java.util.*;

public class SequencedCollections
{
    public static void main(String[] args)
    {
        List<Integer> list = List.of(1, 2, 3);
        SortedSet<Integer> set = new TreeSet<>(list);
        Deque<Integer> queue = new ArrayDeque<>(list);
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(list);

        // (0) Show how iterators and streams provide consistent sequenced access to collections for iteration.
        // (1) Get the first element from a List, Set, SortedSet, LinkedHashSet and Deque
        // (2) Get the last element from a List, Set, SortedSet, LinkedHashSet and Deque
        // (3) Get the first element with SequencedCollection interface.
        // (4) Get the last element with SequencedCollection interface.
        // (5) Review the SequencedCollection interface.
    }
}
