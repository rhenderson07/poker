package poker.hand;

import java.util.ArrayList;
import java.util.List;

import poker.card.Card;

public class ValueGroupHand implements PokerHand {

    private final HandRankEnum rank;
    private final CardHandFragment valueGroup;
    private final List<Card> remaining;

    public ValueGroupHand(HandRankEnum rank, CardHandFragment valueGroup, List<Card> remaining) {
        super();
        this.rank = rank;
        this.valueGroup = valueGroup;
        this.remaining = remaining;
    }

    @Override
    public HandRankEnum getGetHandRank() {
        return rank;
    }

    @Override
    public List<Card> getGetCards() {
        List<Card> cards = new ArrayList<>();
        cards.addAll(valueGroup.getCards());
        cards.addAll(remaining);

        return cards;
    }
}
