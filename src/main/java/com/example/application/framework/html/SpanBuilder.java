package com.example.application.framework.html;

import com.example.application.framework.ComponentBuilder;
import com.vaadin.flow.component.html.Span;

import java.util.function.Consumer;

public class SpanBuilder extends ComponentBuilder<Span, SpanBuilder, SpanConfigurator> {

    public SpanBuilder(Consumer<SpanConfigurator> configConsumer) {
        super(configConsumer);
    }

    @Override
    protected Span createComponent() {
        return new Span();
    }

    @Override
    protected SpanConfigurator createConfigurator() {
        return new SpanConfigurator();
    }

}
