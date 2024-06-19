package com.example.application.views;

import com.example.application.framework.signals.ListSignal;
import com.example.application.framework.signals.Signal;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import java.util.List;
import java.util.stream.Stream;

import static com.example.application.framework.html.HtmlBuilders.*;
import static com.example.application.framework.signals.Signals.listSignal;
import static com.example.application.framework.signals.Signals.signal;

@Route("hello")
public class HelloWorldView extends HorizontalLayout {

    public static class Card extends Composite<ListItem> {

        public final Signal<String> imageSrc = signal();
        public final Signal<String> imageAlt = signal();
        public final Signal<String> cardTitle = signal();
        public final Signal<String> cardText = signal();
        public final ListSignal<Button> buttons = listSignal();

        public Card() {
            getContent().add(
                    div(outerDiv -> outerDiv
                            .classNames(Background.BASE, Display.FLEX, FlexDirection.COLUMN, Overflow.HIDDEN, BorderRadius.MEDIUM, BoxShadow.SMALL)
                            .children(
                                    div(imageWrapper -> imageWrapper
                                            .classNames(Display.FLEX, Flex.GROW)
                                            .children(image(image -> image
                                                    .classNames(MaxWidth.FULL)
                                                    .src(this.imageSrc)
                                                    .alt(this.imageAlt))
                                            )),
                                    div(innerDiv -> innerDiv
                                            .classNames(Display.FLEX, FlexDirection.COLUMN, Gap.MEDIUM)
                                            .children(
                                                    div(contentWrapper -> contentWrapper
                                                            .classNames(Display.FLEX, FlexDirection.COLUMN, Padding.Top.MEDIUM, Padding.Horizontal.MEDIUM)
                                                            .children(
                                                                    span(span -> span
                                                                            .classNames(FontWeight.SEMIBOLD, FontSize.LARGE)
                                                                            .text(this.cardTitle)
                                                                    ),
                                                                    span(span -> span
                                                                            .classNames(FontSize.SMALL, TextColor.SECONDARY)
                                                                            .text(this.cardText)
                                                                    )
                                                            )),
                                                    div(buttons -> buttons
                                                            .classNames(Display.FLEX, Gap.SMALL, Padding.Bottom.SMALL, Padding.Horizontal.SMALL)
                                                            .children(this.buttons))
                                            ))
                            )
                    ).build());
        }
    }

    public static class CardList extends Composite<UnorderedList> {

        public final ListSignal<Card> cards = listSignal();

        public CardList() {
            getContent().addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);
            getContent().getStyle().set("gridTemplateColumns", "repeat(auto-fill, minmax(200px, 1fr)");
            addAttachListener(event -> {
                var registration = cards.addValueChangeListenerAndCallIt(cards -> {
                    getContent().removeAll();
                    cards.forEach(card -> getContent().add(card));
                });
                addDetachListener(detachEvent -> registration.remove());
            });
        }
    }

    public HelloWorldView() {
        var cardList = new CardList();
        add(cardList);

        cardList.cards.set(Stream.generate(() -> {
                    var card = new Card();
                    card.cardTitle.set("Card title");
                    card.cardText.set("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.");
                    card.imageSrc.set("https://images.unsplash.com/photo-1470770841072-f978cf4d019e?w=640");
                    card.imageAlt.set("Architectural photography of brown wooden house – Luca Bravo");
                    card.buttons.set(List.of(new Button("Hello"), new Button("World")));
                    return card;
                })
                .limit(10).toList());
    }

}