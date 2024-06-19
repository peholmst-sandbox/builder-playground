package com.example.application.views;

import com.example.application.framework.Ref;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import java.net.URI;
import java.util.stream.Stream;

import static com.example.application.framework.Ref.ref;
import static com.example.application.framework.html.HtmlBuilders.*;

@Route("hello")
public class HelloWorldView extends HorizontalLayout {

    public static class Card extends Composite<ListItem> {

        private final Ref<Image> image = ref();
        private final Ref<Span> cardTitle = ref();
        private final Ref<Span> cardText = ref();
        private final Ref<Div> buttons = ref();

        public Card() {
            getContent().add(
                    div(outerDiv -> outerDiv
                            .classNames(Background.BASE, Display.FLEX, FlexDirection.COLUMN, Overflow.HIDDEN, BorderRadius.MEDIUM, BoxShadow.SMALL)
                            .children(
                                    div(imageWrapper -> imageWrapper
                                            .classNames(Display.FLEX, Flex.GROW)
                                            .children(image(image -> image
                                                    .classNames(MaxWidth.FULL))
                                                    .ref(image)
                                            )),
                                    div(innerDiv -> innerDiv
                                            .classNames(Display.FLEX, FlexDirection.COLUMN, Gap.MEDIUM)
                                            .children(
                                                    div(contentWrapper -> contentWrapper
                                                            .classNames(Display.FLEX, FlexDirection.COLUMN, Padding.Top.MEDIUM, Padding.Horizontal.MEDIUM)
                                                            .children(
                                                                    span(span -> span
                                                                            .classNames(FontWeight.SEMIBOLD, FontSize.LARGE))
                                                                            .ref(cardTitle),
                                                                    span(span -> span
                                                                            .classNames(FontSize.SMALL, TextColor.SECONDARY))
                                                                            .ref(cardText)
                                                            )),
                                                    div(buttons -> buttons
                                                            .classNames(Display.FLEX, Gap.SMALL, Padding.Bottom.SMALL, Padding.Horizontal.SMALL))
                                                            .ref(buttons)
                                            ))
                            )
                    ).build());
        }

        public Card withTitle(String title) {
            cardTitle.doIfPresent(span -> span.setText(title));
            return this;
        }

        public Card withText(String text) {
            cardText.doIfPresent(span -> span.setText(text));
            return this;
        }

        public Card withImage(URI src, String alt) {
            image.doIfPresent(image -> {
                image.setSrc(src.toString());
                image.setAlt(alt);
            });
            return this;
        }

        public Card withButton(Button button) {
            button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            button.setClassName(Margin.NONE);
            buttons.doIfPresent(div -> div.add(button));
            return this;
        }
    }

    public static class CardList extends Composite<UnorderedList> {

        public CardList() {
            getContent().addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);
            getContent().getStyle().set("gridTemplateColumns", "repeat(auto-fill, minmax(200px, 1fr)");
        }

        public void add(Card card) {
            getContent().add(card);
        }

        public void add(Card... cards) {
            Stream.of(cards).forEach(this::add);
        }
    }

    public HelloWorldView() {
        var cardList = new CardList();

        cardList.add(
                new Card()
                        .withTitle("Card title")
                        .withText("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.")
                        .withImage(URI.create("https://images.unsplash.com/photo-1470770841072-f978cf4d019e?w=640"), "Architectural photography of brown wooden house – Luca Bravo")
                        .withButton(new Button("Hello")).withButton(new Button("World")),
                new Card()
                        .withTitle("Card title")
                        .withText("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.")
                        .withImage(URI.create("https://images.unsplash.com/photo-1470770841072-f978cf4d019e?w=640"), "Architectural photography of brown wooden house – Luca Bravo")
                        .withButton(new Button("Hello2")).withButton(new Button("World2")
                        ),
                new Card()
                        .withTitle("Card title")
                        .withText("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.")
                        .withImage(URI.create("https://images.unsplash.com/photo-1470770841072-f978cf4d019e?w=640"), "Architectural photography of brown wooden house – Luca Bravo")
                        .withButton(new Button("Hello3")).withButton(new Button("World3")
                        ),
                new Card()
                        .withTitle("Card title")
                        .withText("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.")
                        .withImage(URI.create("https://images.unsplash.com/photo-1470770841072-f978cf4d019e?w=640"), "Architectural photography of brown wooden house – Luca Bravo")
                        .withButton(new Button("Hello4")).withButton(new Button("World4")
                        )
        );
        add(cardList);
    }

}