package com.example.application.framework.html;

import com.example.application.framework.ComponentBuilder;
import com.example.application.framework.ComponentConfigurator;
import com.example.application.framework.signals.ListSignal;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

import java.util.List;

public class DivConfigurator extends ComponentConfigurator<Div, DivConfigurator> {

    private List<ComponentBuilder<?, ?, ?>> children;
    private ListSignal<? extends Component> childrenSignal;

    @Override
    protected void configure(Div component) {
        super.configure(component);
        if (childrenSignal != null) {
            component.addAttachListener(event -> {
                var registration = childrenSignal.addValueChangeListenerAndCallIt(children -> {
                    component.removeAll();
                    children.forEach(component::add);
                });
                component.addDetachListener(detachEvent -> registration.remove());
            });
        } else if (children != null) {
            children.forEach(builder -> component.add(builder.build()));
        }
    }

    public DivConfigurator children(ComponentBuilder<?, ?, ?>... builders) {
        if (childrenSignal != null) {
            throw new IllegalStateException("Children have already been set as a signal");
        }
        this.children = List.of(builders);
        return this;
    }

    public DivConfigurator children(ListSignal<? extends Component> components) {
        if (children != null) {
            throw new IllegalStateException("Children have already been set as a list of builders");
        }
        this.childrenSignal = components;
        return this;
    }
}
