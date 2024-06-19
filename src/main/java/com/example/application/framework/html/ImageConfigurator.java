package com.example.application.framework.html;

import com.example.application.framework.ComponentConfigurator;
import com.example.application.framework.signals.Signal;
import com.vaadin.flow.component.html.Image;

public class ImageConfigurator extends ComponentConfigurator<Image, ImageConfigurator> {

    private Signal<String> srcSignal;
    private Signal<String> altSignal;

    @Override
    protected void configure(Image component) {
        super.configure(component);
        if (srcSignal != null) {
            component.addAttachListener(event -> {
                var registration = srcSignal.addValueChangeListenerAndCallIt(component::setSrc);
                component.addDetachListener(detachEvent -> registration.remove());
            });
        }
        if (altSignal != null) {
            component.addAttachListener(event -> {
                var registration = altSignal.addValueChangeListenerAndCallIt(component::setAlt);
                component.addDetachListener(detachEvent -> registration.remove());
            });
        }
    }

    public ImageConfigurator src(Signal<String> srcSignal) {
        this.srcSignal = srcSignal;
        return this;
    }

    public ImageConfigurator alt(Signal<String> altSignal) {
        this.altSignal = altSignal;
        return this;
    }
}
