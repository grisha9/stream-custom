package ru.rzn.gmyasoedov;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by grisha on 15.01.17.
 */
public class PredicateIterator<E> implements Iterator<E> {
    private Iterator<E> iterator;
    private MyIterable.Predicate<E> predicate;
    private E next;

    public PredicateIterator(Iterator<E> iterator, MyIterable.Predicate<E> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        if (next != null) {
            return true;
        }

        while (iterator.hasNext()) {
            E next = iterator.next();
            if (predicate.apply(next)) {
                this.next = next;
                return true;
            }
        }
        return false;
    }

    @Override
    public E next() {
        if (next != null) {
            E next = this.next;
            this.next = null;
            return next;
        } else {
            while (iterator.hasNext()) {
                E next = iterator.next();
                if (predicate.apply(next)) {
                    return next;
                }
            }
            throw new NoSuchElementException();
        }
    }

    public static void main(String[] args) {
        List<Integer> ts = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        PredicateIterator<Integer> t1 = new PredicateIterator<>(ts.iterator(), integer -> integer % 2 == 0);
        PredicateIterator<Integer> t2 = new PredicateIterator<>(t1, integer -> integer > 4);


        while (t2.hasNext()) {
            System.out.println(t2.next());
        }
    }
}
