package ru.rzn.gmyasoedov;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by G.Myasoedov on 13.01.2017.
 */
public class MyIterableImpl<E> implements MyIterable<E> {
    private List<E> list;

    public MyIterableImpl(List<E> list) {
        this.list = list;
    }

    public MyIterable<E> filter(@Nonnull Predicate<E> predicate) {
        return null;
    }

    public <T> MyIterable<T> transform(@Nonnull Function<E, T> transformer) {
        return null;
    }

    public <T> T aggregate(@Nullable T initValue, @Nonnull Aggregator<E, T> aggregator) {
        return null;
    }

    public SortedSet<E> toSet(@Nonnull Comparator<E> comarator) {
        return null;
    }

    public List<E> toList() {
        return null;
    }

    @Nullable
    public E findFirst(@Nonnull Predicate<E> predicate) {
        return null;
    }

    public Iterator<E> iterator() {
        return new ArrayList<E>().iterator();
    }

    public static <E> MyIterable<E> of(E... es) {
        return new MyIterableImpl<E>(Arrays.asList(es));
    }
}
