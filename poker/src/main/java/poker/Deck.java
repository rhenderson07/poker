package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import poker.card.Card;
import poker.card.CardSuitEnum;
import poker.card.CardValueEnum;

public class Deck {
    // private List<Card> cards;
    //
    // public Deck() {
    // cards = buildDeck();
    // }

    public static List<Card> buildDeck() {
        List<Card> cards = new ArrayList<>();
        for (CardSuitEnum suit : CardSuitEnum.values()) {
            for (CardValueEnum value : CardValueEnum.values()) {
                cards.add(new Card(value, suit));
            }
        }
        return cards;
    }

    public static List<Card> buildDeckUsingStreams() {
        return Arrays.stream(CardSuitEnum.values()) //
                .flatMap(s -> Arrays.stream(CardValueEnum.values()).map(v -> new Card(v, s))) //
                .collect(Collectors.toList());
    }
}
