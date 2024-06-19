package com.example.application.framework;

import com.vaadin.flow.component.Component;

import java.util.function.Consumer;

public class Ref<C extends Component> {
    private C component;

    public void set(C component) {
        this.component = component;
    }

    public void doIfPresent(Consumer<C> consumer) {
        if (component != null) {
            consumer.accept(component);
        }
    }

    public static <C extends Component> Ref<C> ref() {
        return new Ref<>();
    }
}
