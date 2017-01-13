package ru.rzn.gmyasoedov;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by G.Myasoedov on 13.01.2017.
 */
public class MyIterableImpl<E> implements MyIterable<E> {
    private List<E> list;

    public MyIterableImpl(List<E> list) {
        this.list = list;
    }

    @Override
    public MyIterable<E> filter(@Nonnull Predicate<E> predicate) {
        return null;
    }

    @Override
    public <T> MyIterable<T> transform(@Nonnull Function<E, T> transformer) {
        return null;
    }

    @Override
    public <T> T aggregate(@Nullable T initValue, @Nonnull Aggregator<E, T> aggregator) {
        return null;
    }

    @Override
    public SortedSet<E> toSet(@Nonnull Comparator<E> comarator) {
        return list.stream().collect(Collectors.toCollection(() -> new TreeSet<>(comarator)));
    }

    @Override
    public List<E> toList() {
        return new ArrayList<>(list);
    }

    @Nullable
    @Override
    public E findFirst(@Nonnull Predicate<E> predicate) {
        return list.stream().filter(predicate::apply).findFirst().orElse(null);
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayList<E>().iterator();
    }

    public static <E> MyIterable<E> of(E... es) {
        return new MyIterableImpl<>(Arrays.asList(es));
    }
}
