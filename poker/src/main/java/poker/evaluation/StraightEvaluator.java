package poker.evaluation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import poker.card.Card;
import poker.card.CardValueEnum;

public class StraightEvaluator {

    private List<List<CardValueEnum>> getStraightWindows(int windowSize) {
        // add ACE to both ends
        List<CardValueEnum> straightSequence = new ArrayList<>(Arrays.asList(CardValueEnum.values()));
        straightSequence.add(CardValueEnum.ACE);

        List<List<CardValueEnum>> straightWindows = new ArrayList<>();
        for (int i = 0; i < straightSequence.size() - windowSize + 1; i++) {
            straightWindows.add(straightSequence.subList(i, i + windowSize));
        }

        straightWindows.sort(new ListItemCompare<>());
        return straightWindows;
    }

    private Optional<List<Card>> mapToCardsContainingValues(Collection<Card> cards,
            Collection<CardValueEnum> requiredCardValues) {

        List<Card> cardMatchingRequiredValues = requiredCardValues.stream() //
                .map(x -> findCardWithValue(cards, x)) //
                .filter(Optional::isPresent) //
                .map(Optional::get) //
                .collect(Collectors.toList());

        if (cardMatchingRequiredValues.size() == requiredCardValues.size()) {
            return Optional.of(cardMatchingRequiredValues);
        }

        return Optional.empty();
    }

    private Optional<Card> findCardWithValue(Collection<Card> cards, CardValueEnum value) {
        return cards.stream().filter(c -> c.getValueType() == value).findFirst();
    }

    public Optional<List<Card>> findStraight(Collection<Card> cards, int straightLength) {
        List<List<CardValueEnum>> straightWindows = getStraightWindows(straightLength);
        return straightWindows.stream()//
                .map(s -> mapToCardsContainingValues(cards, s)) //
                .filter(Optional::isPresent) //
                .map(Optional::get) //
                .findFirst();
    }
}
