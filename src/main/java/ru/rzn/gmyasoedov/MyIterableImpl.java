package ru.rzn.gmyasoedov;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by G.Myasoedov on 13.01.2017.
 */
public class MyIterableImpl<E> implements MyIterable<E> {
    private Iterator<E> iterator;

    private MyIterableImpl(Iterator<E> iterator) {
        this.iterator = Objects.requireNonNull(iterator);
    }

    @Override
    public MyIterable<E> filter(@Nonnull Predicate<E> predicate) {
        PredicateIterator<E> predicateIterator = new PredicateIterator<>(iterator, predicate);
        return new MyIterableImpl<>(predicateIterator);
    }

    @Override
    public <T> MyIterable<T> transform(@Nonnull Function<E, T> transformer) {
        TransformIterator<E, T> transformIterator = new TransformIterator<>(iterator, transformer);
        return new MyIterableImpl<>(transformIterator);
    }

    @Override
    public <T> T aggregate(@Nullable T initValue, @Nonnull Aggregator<E, T> aggregator) {
        T result = initValue;
        while (iterator.hasNext()) {
            E next = iterator.next();
            result = aggregator.apply(result, next);
        }
        return result;
    }

    @Override
    public SortedSet<E> toSet(@Nonnull Comparator<E> comarator) {
        TreeSet<E> result = new TreeSet<E>(comarator);
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }

    @Override
    public List<E> toList() {
        ArrayList<E> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }

    @Nullable
    @Override
    public E findFirst(@Nonnull Predicate<E> predicate) {
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (predicate.apply(next)) {
                return next;
            }
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayList<E>().iterator();
    }

    @SafeVarargs
    public static <E> MyIterable<E> of(E... es) {
        return new MyIterableImpl<>(Arrays.asList(es).iterator());
    }
}
