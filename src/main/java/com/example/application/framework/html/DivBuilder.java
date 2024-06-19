package com.example.application.framework.html;

import com.example.application.framework.ComponentBuilder;
import com.vaadin.flow.component.html.Div;

import java.util.function.Consumer;

public class DivBuilder extends ComponentBuilder<Div, DivBuilder, DivConfigurator> {

    public DivBuilder(Consumer<DivConfigurator> configConsumer) {
        super(configConsumer);
    }

    @Override
    protected Div createComponent() {
        return new Div();
    }

    @Override
    protected DivConfigurator createConfigurator() {
        return new DivConfigurator();
    }

}
