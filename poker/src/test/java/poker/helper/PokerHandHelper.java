package poker.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import poker.card.Card;
import poker.card.CardSuitEnum;
import poker.card.CardValueEnum;

public class PokerHandHelper {

    private static <T> Stream<T> getEveryOtherItemStream(List<T> list) {
        return IntStream.range(0, list.size()) //
                .filter(n -> n % 2 == 0) //
                .mapToObj(n -> list.get(n));
    }

    private static List<Card> getMatchingValue(CardValueEnum value, int count) {
        return CardHelper.getMatchingValue(value).stream() // .
                .limit(count) //
                .collect(Collectors.toList());
    }

    private static List<Card> getMatchingSuit(CardSuitEnum suit, int count) {
        List<Card> cards = CardHelper.getSuitOrdered(suit);

        return getEveryOtherItemStream(cards) //
                .limit(count) //
                .collect(Collectors.toList());
    }

    private static List<Card> getFiller() {
        List<Card> cards = new ArrayList<>();
        cards.add(CardHelper.getEight());
        cards.add(CardHelper.getTwo());
        return cards;
    }

    public static List<Card> getHighCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(CardHelper.getAce());
        cards.add(CardHelper.getQueen());
        cards.add(CardHelper.getJack());
        cards.add(CardHelper.getNine());
        cards.add(CardHelper.getSeven());
        cards.add(CardHelper.getFive());
        cards.add(CardHelper.getTwo());
        return cards;
    }

    public static List<Card> getPair() {
        // pair
        List<Card> cards = getMatchingValue(CardValueEnum.SIX, 2);

        // filler
        cards.add(CardHelper.getJack());
        cards.add(CardHelper.getNine());
        cards.add(CardHelper.getSeven());
        cards.add(CardHelper.getFive());
        cards.add(CardHelper.getTwo());
        return cards;
    }

    public static List<Card> getTwoPair() {
        // two pair
        List<Card> cards = new ArrayList<>();

        cards.addAll(getMatchingValue(CardValueEnum.SIX, 2));
        cards.addAll(getMatchingValue(CardValueEnum.FOUR, 2));

        // filler
        cards.add(CardHelper.getQueen());
        cards.add(CardHelper.getFive());
        cards.add(CardHelper.getTwo());
        return cards;
    }

    public static List<Card> getThreeOfAKind() {
        // three of a kind
        List<Card> cards = new ArrayList<>();
        cards.addAll(getMatchingValue(CardValueEnum.SIX, 3));

        // filler
        cards.add(CardHelper.getJack());
        cards.add(CardHelper.getNine());
        cards.add(CardHelper.getSeven());
        cards.add(CardHelper.getFive());
        return cards;
    }

    public static List<Card> getStraight() {
        // straight
        List<Card> cards = new ArrayList<>();
        cards.add(CardHelper.getFour());
        cards.add(CardHelper.getFive());
        cards.add(CardHelper.getSix());
        cards.add(CardHelper.getSeven());
        cards.add(CardHelper.getEight());

        // filler
        cards.add(CardHelper.getJack());
        cards.add(CardHelper.getAce());
        return cards;
    }

    public static List<Card> getBottomStraight() {
        // straight
        List<Card> cards = new ArrayList<>();
        cards.add(CardHelper.getAce());
        cards.add(CardHelper.getTwo());
        cards.add(CardHelper.getThree());
        cards.add(CardHelper.getFour());
        cards.add(CardHelper.getFive());

        // filler
        cards.add(CardHelper.getJack());
        cards.add(CardHelper.getAce());
        return cards;
    }

    public static List<Card> getTopStraight() {
        // straight
        List<Card> cards = new ArrayList<>();
        cards.add(CardHelper.getAce());
        cards.add(CardHelper.getKing());
        cards.add(CardHelper.getQueen());
        cards.add(CardHelper.getJack());
        cards.add(CardHelper.getTen());

        // filler
        cards.add(CardHelper.getJack());
        cards.add(CardHelper.getAce());
        return cards;
    }

    public static List<Card> getPartialStraight() {
        // parital straight
        List<Card> cards = new ArrayList<>();
        cards.add(CardHelper.getAce());
        cards.add(CardHelper.getKing());
        cards.add(CardHelper.getQueen());
        cards.add(CardHelper.getJack());
        cards.add(CardHelper.getNine());

        // filler
        cards.add(CardHelper.getJack());
        cards.add(CardHelper.getAce());
        return cards;
    }

    public static List<Card> getWrapAroundStraight() {
        // wrap around straight
        List<Card> cards = new ArrayList<>();
        cards.add(CardHelper.getTwo());
        cards.add(CardHelper.getAce());
        cards.add(CardHelper.getKing());
        cards.add(CardHelper.getQueen());
        cards.add(CardHelper.getJack());

        // filler
        cards.add(CardHelper.getJack());
        cards.add(CardHelper.getAce());
        return cards;
    }

    public static List<Card> getFlush() {
        List<Card> cards = getMatchingSuit(CardSuitEnum.DIAMOND, 5);
        cards.addAll(getFiller());
        return cards;
    }

    public static List<Card> getFourOfAKind() {
        // four of a kind
        List<Card> cards = new ArrayList<>();
        cards.addAll(getMatchingValue(CardValueEnum.SIX, 4));

        // filler
        cards.add(CardHelper.getNine());
        cards.add(CardHelper.getSeven());
        cards.add(CardHelper.getFive());
        return cards;
    }

    public static List<Card> getFullHouse() {
        List<Card> cards = new ArrayList<>();
        cards.addAll(getMatchingValue(CardValueEnum.QUEEN, 3));
        cards.addAll(getMatchingValue(CardValueEnum.SIX, 2));
        cards.addAll(getFiller());
        return cards;
    }

    public static List<Card> getStraightFlush() {
        List<Card> cards = CardHelper.getSuitOrdered(CardSuitEnum.DIAMOND).stream() //
                .limit(5) //
                .collect(Collectors.toList());

        cards.addAll(getFiller());
        return cards;
    }
}
