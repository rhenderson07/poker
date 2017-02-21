package poker.helper;

import java.util.ArrayList;
import java.util.List;

import poker.card.Card;
import poker.card.CardSuitEnum;
import poker.card.CardValueEnum;

public class CardHelper {

    public static Card getAce() {
        return new Card(CardValueEnum.ACE, CardSuitEnum.CLUB);
    }

    public static Card getKing() {
        return new Card(CardValueEnum.KING, CardSuitEnum.SPADE);
    }

    public static Card getQueen() {
        return new Card(CardValueEnum.QUEEN, CardSuitEnum.DIAMOND);
    }

    public static Card getJack() {
        return new Card(CardValueEnum.JACK, CardSuitEnum.HEART);
    }

    public static Card getTen() {
        return new Card(CardValueEnum.TEN, CardSuitEnum.CLUB);
    }

    public static Card getNine() {
        return new Card(CardValueEnum.NINE, CardSuitEnum.SPADE);
    }

    public static Card getEight() {
        return new Card(CardValueEnum.EIGHT, CardSuitEnum.DIAMOND);
    }

    public static Card getSeven() {
        return new Card(CardValueEnum.SEVEN, CardSuitEnum.HEART);
    }

    public static Card getSix() {
        return new Card(CardValueEnum.SIX, CardSuitEnum.CLUB);
    }

    public static Card getFive() {
        return new Card(CardValueEnum.FIVE, CardSuitEnum.SPADE);
    }

    public static Card getFour() {
        return new Card(CardValueEnum.FOUR, CardSuitEnum.DIAMOND);
    }

    public static Card getThree() {
        return new Card(CardValueEnum.THREE, CardSuitEnum.HEART);
    }

    public static Card getTwo() {
        return new Card(CardValueEnum.TWO, CardSuitEnum.CLUB);
    }

    public static List<Card> getSuitOrdered(CardSuitEnum suit) {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardValueEnum.TWO, suit));
        cards.add(new Card(CardValueEnum.THREE, suit));
        cards.add(new Card(CardValueEnum.FOUR, suit));
        cards.add(new Card(CardValueEnum.FIVE, suit));
        cards.add(new Card(CardValueEnum.SIX, suit));
        cards.add(new Card(CardValueEnum.SEVEN, suit));
        cards.add(new Card(CardValueEnum.EIGHT, suit));
        cards.add(new Card(CardValueEnum.NINE, suit));
        cards.add(new Card(CardValueEnum.TEN, suit));
        cards.add(new Card(CardValueEnum.JACK, suit));
        cards.add(new Card(CardValueEnum.QUEEN, suit));
        cards.add(new Card(CardValueEnum.KING, suit));
        cards.add(new Card(CardValueEnum.ACE, suit));
        return cards;
    }

    public static List<Card> getMatchingValue(CardValueEnum value) {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(value, CardSuitEnum.CLUB));
        cards.add(new Card(value, CardSuitEnum.HEART));
        cards.add(new Card(value, CardSuitEnum.SPADE));
        cards.add(new Card(value, CardSuitEnum.DIAMOND));
        return cards;
    }

}
