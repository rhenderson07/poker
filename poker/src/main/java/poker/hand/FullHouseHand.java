package poker.hand;

import java.util.ArrayList;
import java.util.List;

import poker.card.Card;

public class FullHouseHand implements PokerHand {

    private final CardHandFragment three;
    private final CardHandFragment two;

    public FullHouseHand(CardHandFragment three, CardHandFragment two) {
        super();
        this.three = three;
        this.two = two;
    }

    @Override
    public HandRankEnum getGetHandRank() {
        return HandRankEnum.FULL_HOUSE;
    }

    @Override
    public List<Card> getGetCards() {
        List<Card> cards = new ArrayList<>();
        cards.addAll(three.getCards());
        cards.addAll(two.getCards());

        return cards;
    }

}
