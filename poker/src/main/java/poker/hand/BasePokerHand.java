package poker.hand;

import java.util.ArrayList;
import java.util.List;

import poker.card.Card;

public class BasePokerHand implements PokerHand {

    private final HandRankEnum rank;
    private final List<Card> cards;

    public BasePokerHand(HandRankEnum rank, List<Card> cards) {
        this.rank = rank;
        this.cards = cards;
    }

    @Override
    public HandRankEnum getGetHandRank() {
        return rank;
    }

    @Override
    public List<Card> getGetCards() {
        return new ArrayList<>(cards);
    }

    @Override
    public String toString() {
        return "[rank=" + rank + ", cards=" + cards + "]";
    }
}
