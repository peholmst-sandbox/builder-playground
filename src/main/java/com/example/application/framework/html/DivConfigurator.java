package com.example.application.framework.html;

import com.example.application.framework.ComponentBuilder;
import com.example.application.framework.ComponentConfigurator;
import com.vaadin.flow.component.html.Div;

import java.util.List;

public class DivConfigurator extends ComponentConfigurator<Div, DivConfigurator> {

    private List<ComponentBuilder<?, ?, ?>> children;

    @Override
    protected void configure(Div component) {
        super.configure(component);
        if (children != null) {
            children.forEach(builder -> component.add(builder.build()));
        }
    }

    public DivConfigurator children(ComponentBuilder<?, ?, ?>... builders) {
        this.children = List.of(builders);
        return this;
    }
}
