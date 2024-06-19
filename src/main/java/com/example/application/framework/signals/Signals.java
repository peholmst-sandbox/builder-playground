package com.example.application.framework.signals;

import com.vaadin.flow.shared.Registration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class Signals {

    public static <T> Signal<T> signal() {
        return new Signal<T>() {

            private final List<Consumer<T>> valueChangeListeners = new ArrayList<>();
            private T value = null;

            @Override
            public T value() {
                return value;
            }

            @Override
            public void set(T value) {
                this.value = value;
                List.copyOf(valueChangeListeners).forEach(listener -> listener.accept(value));
            }

            @Override
            public Registration addValueChangeListener(Consumer<T> valueChangeListener) {
                this.valueChangeListeners.add(requireNonNull(valueChangeListener));
                return () -> this.valueChangeListeners.remove(valueChangeListener);
            }
        };
    }

    public static <T> ListSignal<T> listSignal() {
        return new ListSignal<T>() {
            private final List<Consumer<List<T>>> valueChangeListeners = new ArrayList<>();
            private List<T> value = new ArrayList<>();

            @Override
            public List<T> value() {
                return List.copyOf(value);
            }

            @Override
            public void set(List<T> value) {
                this.value = new ArrayList<>(requireNonNull(value));
                notifyListeners();
            }

            @Override
            public Registration addValueChangeListener(Consumer<List<T>> valueChangeListener) {
                this.valueChangeListeners.add(requireNonNull(valueChangeListener));
                return () -> this.valueChangeListeners.remove(valueChangeListener);
            }

            @Override
            public Stream<T> stream() {
                return value.stream();
            }

            @Override
            public void add(T item) {
                this.value.add(item);
                notifyListeners();
            }

            @Override
            public void remove(T item) {
                this.value.remove(item);
                notifyListeners();
            }

            private void notifyListeners() {
                List.copyOf(valueChangeListeners).forEach(listener -> listener.accept(value));
            }
        };
    }
}
