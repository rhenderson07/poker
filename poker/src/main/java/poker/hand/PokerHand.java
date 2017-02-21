package poker.hand;

import java.util.List;

import poker.card.Card;

public interface PokerHand {
    public HandRankEnum getGetHandRank();
    public List<Card> getGetCards();
}
