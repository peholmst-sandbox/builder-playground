package com.example.application.framework;

import com.vaadin.flow.component.Component;

import java.util.function.Consumer;

public abstract class ComponentBuilder<C extends Component, B extends ComponentBuilder<C, B, CC>, CC extends ComponentConfigurator<C, CC>> {

    private final CC configurator;
    private Ref<C> ref;

    public ComponentBuilder(Consumer<CC> configConsumer) {
        this.configurator = createConfigurator();
        configConsumer.accept(this.configurator);
    }

    public C build() {
        var component = createComponent();
        if (configurator != null) {
            configurator.configure(component);
        }
        if (ref != null) {
            ref.set(component);
        }
        return component;
    }

    protected abstract C createComponent();

    protected abstract CC createConfigurator();

    public B ref(Ref<C> ref) {
        this.ref = ref;
        return (B) this;
    }
}
