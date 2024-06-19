package com.example.application.framework.html;

import com.example.application.framework.ComponentBuilder;
import com.vaadin.flow.component.html.Image;

import java.util.function.Consumer;

public class ImageBuilder extends ComponentBuilder<Image, ImageBuilder, ImageConfigurator> {

    public ImageBuilder(Consumer<ImageConfigurator> configConsumer) {
        super(configConsumer);
    }

    @Override
    protected Image createComponent() {
        return new Image();
    }

    @Override
    protected ImageConfigurator createConfigurator() {
        return new ImageConfigurator();
    }

}
