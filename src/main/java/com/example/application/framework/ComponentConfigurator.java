package com.example.application.framework;

import com.vaadin.flow.component.Component;

import java.util.List;

public abstract class ComponentConfigurator<C extends Component, CC extends ComponentConfigurator<C, CC>> {

    private List<String> classNames;

    protected void configure(C component) {
        if (classNames != null) {
            classNames.forEach(component::addClassName);
        }
    }

    public CC classNames(String... classNames) {
        this.classNames = List.of(classNames);
        return (CC) this;
    }

}
