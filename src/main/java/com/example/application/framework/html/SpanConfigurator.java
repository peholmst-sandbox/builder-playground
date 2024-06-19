package com.example.application.framework.html;

import com.example.application.framework.ComponentConfigurator;
import com.example.application.framework.signals.Signal;
import com.vaadin.flow.component.html.Span;

public class SpanConfigurator extends ComponentConfigurator<Span, SpanConfigurator> {

    private Signal<String> textSignal;

    @Override
    protected void configure(Span component) {
        super.configure(component);
        if (textSignal != null) {
            component.addAttachListener(event -> {
                var registration = textSignal.addValueChangeListenerAndCallIt(component::setText);
                component.addDetachListener(detachEvent -> registration.remove());
            });
        }
    }

    public SpanConfigurator text(Signal<String> textSignal) {
        this.textSignal = textSignal;
        return this;
    }
}
