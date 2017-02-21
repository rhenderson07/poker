package poker.evaluation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import poker.card.Card;
import poker.hand.CardHandFragment;

public class ValueGroupEvaluator {

    private Optional<List<Card>> findCardValueGroupBySize(Collection<Card> cards, int groupSize) {
        return getCardsGroupedByValue(cards).stream()//
                .filter(x -> x.size() == groupSize)//
                .findFirst();
    }

    public Collection<List<Card>> getCardsGroupedByValue(Collection<Card> cards) {
        return cards.stream()//
                .sorted()//
                .collect(Collectors.groupingBy(Card::getValueType))//
                .values();
    }

    public Optional<CardHandFragment> findPair(Collection<Card> cards) {
        return findCardValueGroupBySize(cards, 2).map(CardHandFragment::new);
    }

    public Optional<CardHandFragment> findThreeOfAKind(Collection<Card> cards) {
        return findCardValueGroupBySize(cards, 3).map(CardHandFragment::new);
    }

    public Optional<CardHandFragment> findFourOfAKind(Collection<Card> cards) {
        return findCardValueGroupBySize(cards, 4).map(CardHandFragment::new);
    }
}
