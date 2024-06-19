package com.example.application.framework.signals;

import java.util.List;
import java.util.stream.Stream;

public interface ListSignal<T> extends Signal<List<T>> {
    Stream<T> stream();

    void add(T item);

    void remove(T item);
}
