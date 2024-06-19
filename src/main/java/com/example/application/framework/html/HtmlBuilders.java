package com.example.application.framework.html;

import java.util.function.Consumer;

public class HtmlBuilders {
    public static DivBuilder div(Consumer<DivConfigurator> configurator) {
        return new DivBuilder(configurator);
    }

    public static ImageBuilder image(Consumer<ImageConfigurator> configurator) {
        return new ImageBuilder(configurator);
    }

    public static SpanBuilder span(Consumer<SpanConfigurator> configurator) {
        return new SpanBuilder(configurator);
    }
}
