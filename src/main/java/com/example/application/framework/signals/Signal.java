package com.example.application.framework.signals;

import com.vaadin.flow.shared.Registration;

import java.util.function.Consumer;

public interface Signal<T> {
    T value();

    void set(T value);

    Registration addValueChangeListener(Consumer<T> valueChangeListener);

    default Registration addValueChangeListenerAndCallIt(Consumer<T> valueChangeListener) {
        var reg = addValueChangeListener(valueChangeListener);
        valueChangeListener.accept(value());
        return reg;
    }
}
