package poker.hand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import poker.card.Card;

public class CardHandFragment {
    private final List<Card> cards;

    public CardHandFragment(Collection<Card> cards) {

        this.cards = new ArrayList<>(cards);
    }

    public List<Card> getCards() {
        return cards;
    }
}
