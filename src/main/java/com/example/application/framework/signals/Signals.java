package com.example.application.framework.signals;

import com.vaadin.flow.shared.Registration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
                this.valueChangeListeners.add(valueChangeListener);
                return () -> this.valueChangeListeners.remove(valueChangeListener);
            }
        };
    }
}
