package com.monotonic.collections._6_operations.after;

import java.util.*;

public class SequencedCollections
{
    public static void main(String[] args)
    {
        List<Integer> list = List.of(1, 2, 3);
        SortedSet<Integer> set = new TreeSet<>(list);
        Deque<Integer> queue = new ArrayDeque<>(list);
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(list);

        // (0) Show how iterators and streams provide consistent sequenced access to
        // collections for iteration.
        for (var x : list) {
            System.out.println(x);
        }
        set.stream().forEach(System.out::println);

        // (1) Get the first element from a List, Set, SortedSet, LinkedHashSet and Deque
        System.out.println(list.get(0));
        System.out.println(set.first());
        System.out.println(queue.getFirst());
        System.out.println(hashSet.iterator().next());

        // (2) Get the last element from a List, Set, SortedSet, LinkedHashSet and Deque
        System.out.println(list.get(list.size() - 1));
        System.out.println(set.last());
        System.out.println(queue.getLast());
//        System.out.println(hashSet); (not really possible)

        // (3) Get the first element with SequencedCollection interface.
        System.out.println(list.getFirst());
        System.out.println(set.getFirst());
        System.out.println(queue.getFirst());
        System.out.println(hashSet.getFirst());

        // (4) Get the last element with SequencedCollection interface.
        System.out.println(list.getLast());
        System.out.println(set.getLast());
        System.out.println(queue.getLast());
        System.out.println(hashSet.getLast());

        // (5) Review the SequencedCollection interface.
        for (var x : list.reversed()) {
            System.out.println(x);
        }
    }
}
