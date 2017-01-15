package ru.rzn.gmyasoedov;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * Created by grisha on 15.01.17.
 */
public class TransformIterator<E, T> implements Iterator<T> {
    private Iterator<E> iterator;
    private MyIterable.Function<E, T> function;

    public TransformIterator(Iterator<E> eIterator, MyIterable.Function<E, T> function) {
        this.iterator = eIterator;
        this.function = function;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return function.apply(iterator.next());
    }

    public static void main(String[] args) {
        List<Integer> ts = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        PredicateIterator<Integer> t1 = new PredicateIterator<>(ts.iterator(), integer -> integer % 2 == 0);
        TransformIterator<Integer, String> t2 = new TransformIterator<>(t1, integer -> String.valueOf(integer) + "!");
        PredicateIterator<String> t3 = new PredicateIterator<>(t2, s -> s.startsWith("2") || s.startsWith("4"));
        TransformIterator<String, Integer> t4 = new TransformIterator<>(t3, integer -> integer.length());


        while (t3.hasNext()) {
            System.out.println(t3.next());
        }
    }
}
