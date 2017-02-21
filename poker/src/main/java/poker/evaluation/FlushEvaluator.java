package poker.evaluation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import poker.card.Card;

public class FlushEvaluator {

    private Collection<List<Card>> getCardsGroupedBySuit(Collection<Card> cards) {
        return cards.stream()//
                .sorted()//
                .collect(Collectors.groupingBy(Card::getSuit))//
                .values();
    }

    public Optional<List<Card>> findFlush(Collection<Card> cards, int minFlushSize) {
        return getCardsGroupedBySuit(cards).stream()//
                .filter(l -> l.size() >= minFlushSize)//
                .sorted(new ListItemCompare<>())
                .findFirst();
    }
}
