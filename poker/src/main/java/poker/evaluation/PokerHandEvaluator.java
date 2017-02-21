package poker.evaluation;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import poker.card.Card;
import poker.hand.BasePokerHand;
import poker.hand.CardHandFragment;
import poker.hand.FullHouseHand;
import poker.hand.HandRankEnum;
import poker.hand.PokerHand;
import poker.hand.ValueGroupHand;

public class PokerHandEvaluator {

    private static final int POKER_HAND_SIZE = 5;

    private ValueGroupEvaluator valueGroupEvaluator;
    private StraightEvaluator straightEvaluator;
    private FlushEvaluator flushEvaluator;
    private static final List<HandRankEnum> STANDARD_HAND_RANK_ORDER = Arrays.asList(HandRankEnum.values());

    public PokerHandEvaluator() {
        this.valueGroupEvaluator = new ValueGroupEvaluator();
        this.straightEvaluator = new StraightEvaluator();
        this.flushEvaluator = new FlushEvaluator();
    }

    public PokerHand findBestHand(Collection<Card> cards) {
        Stream<HandRankEnum> handRankOrderStream = STANDARD_HAND_RANK_ORDER.stream();

        return handRankOrderStream //
                .map(x -> findHandOfRank(cards, x)) //
                .filter(Optional::isPresent) //
                .map(Optional::get) //
                .findFirst() //
                .get();
    }

    private Optional<PokerHand> findHandOfRank(Collection<Card> cards, HandRankEnum rank) {
        switch (rank) {
        case STRAIGHT_FLUSH:
            return findStraightFlushHand(cards);
        case FOUR_OF_A_KIND:
            return findFourOfAKindHand(cards);
        case FULL_HOUSE:
            return findFullHouseHand(cards);
        case FLUSH:
            return findFlushHand(cards);
        case STRAIGHT:
            return findStraightHand(cards);
        case THREE_OF_A_KIND:
            return findThreeOfAKindHand(cards);
        case TWO_PAIR:
            return findTwoPairHand(cards);
        case PAIR:
            return findPairHand(cards);
        default:
            return findHighCardHand(cards);
        }
    }

    private List<Card> makeSortedHand(Collection<Card> cards) {
        return cards.stream().sorted().limit(POKER_HAND_SIZE).collect(Collectors.toList());
    }

    private List<Card> getRemainingCards(Collection<Card> list, Collection<Card> excludeValues) {
        int limitSize = POKER_HAND_SIZE - excludeValues.size();
        return list.stream().filter(x -> !excludeValues.contains(x)) //
                .sorted() //
                .limit(limitSize) //
                .collect(Collectors.toList());
    }

    public Optional<PokerHand> findHighCardHand(Collection<Card> cards) {
        List<Card> highCards = cards.stream().sorted().limit(POKER_HAND_SIZE).collect(Collectors.toList());
        return Optional.of(new BasePokerHand(HandRankEnum.HIGH_CARD, highCards));
    }

    public Optional<PokerHand> findPairHand(Collection<Card> cards) {
        Optional<CardHandFragment> pairOpt = valueGroupEvaluator.findPair(cards);

        if (pairOpt.isPresent()) {
            CardHandFragment pair = pairOpt.get();
            List<Card> remainingCards = getRemainingCards(cards, pair.getCards());
            return Optional.of(new ValueGroupHand(HandRankEnum.PAIR, pair, remainingCards));
        }

        return Optional.empty();
    }

    public Optional<PokerHand> findTwoPairHand(Collection<Card> cards) {
        final int pairsToGet = 2;
        final int cardsInPair = 2;

        List<Card> twoPairCards = valueGroupEvaluator.getCardsGroupedByValue(cards).stream()//
                .filter(x -> x.size() == cardsInPair) //
                .limit(pairsToGet) //
                .flatMap(Collection::stream) //
                .collect(Collectors.toList());

        if (twoPairCards.size() == cardsInPair * pairsToGet) {
            List<Card> remaining = getRemainingCards(cards, twoPairCards);
            CardHandFragment twoPairFragment = new CardHandFragment(twoPairCards);
            return Optional.of(new ValueGroupHand(HandRankEnum.TWO_PAIR, twoPairFragment, remaining));
        }

        return Optional.empty();
    }

    public Optional<PokerHand> findThreeOfAKindHand(Collection<Card> cards) {
        Optional<CardHandFragment> threeOpt = valueGroupEvaluator.findThreeOfAKind(cards);

        if (threeOpt.isPresent()) {
            CardHandFragment three = threeOpt.get();
            List<Card> remainingCards = getRemainingCards(cards, three.getCards());
            return Optional.of(new ValueGroupHand(HandRankEnum.THREE_OF_A_KIND, three, remainingCards));
        }

        return Optional.empty();
    }

    public Optional<PokerHand> findStraightHand(Collection<Card> cards) {
        Optional<List<Card>> straightOpt = straightEvaluator.findStraight(cards, POKER_HAND_SIZE);

        if (straightOpt.isPresent()) {
            List<Card> straightCards = straightOpt.get();
            return Optional.of(new BasePokerHand(HandRankEnum.STRAIGHT, straightCards));
        }

        return Optional.empty();
    }

    public Optional<PokerHand> findFlushHand(Collection<Card> cards) {
        Optional<List<Card>> flushOpt = flushEvaluator.findFlush(cards, POKER_HAND_SIZE);

        if (flushOpt.isPresent()) {
            List<Card> flushCards = makeSortedHand(flushOpt.get());
            return Optional.of(new BasePokerHand(HandRankEnum.FLUSH, flushCards));
        }

        return Optional.empty();
    }

    public Optional<PokerHand> findFourOfAKindHand(Collection<Card> cards) {
        Optional<CardHandFragment> fourOpt = valueGroupEvaluator.findFourOfAKind(cards);

        if (fourOpt.isPresent()) {
            CardHandFragment four = fourOpt.get();
            List<Card> remainingCards = getRemainingCards(cards, four.getCards());
            return Optional.of(new ValueGroupHand(HandRankEnum.FOUR_OF_A_KIND, four, remainingCards));
        }

        return Optional.empty();
    }

    public Optional<PokerHand> findFullHouseHand(Collection<Card> cards) {
        Optional<CardHandFragment> three = valueGroupEvaluator.findThreeOfAKind(cards);
        Optional<CardHandFragment> two = valueGroupEvaluator.findPair(cards);

        if (three.isPresent() && two.isPresent()) {
            return Optional.of(new FullHouseHand(three.get(), two.get()));
        }

        return Optional.empty();
    }

    public Optional<PokerHand> findStraightFlushHand(Collection<Card> cards) {
        Optional<List<Card>> flushOpt = flushEvaluator.findFlush(cards, POKER_HAND_SIZE);

        if (flushOpt.isPresent()) {
            Optional<List<Card>> straightFlushOpt = straightEvaluator.findStraight(flushOpt.get(),
                    POKER_HAND_SIZE);

            if (straightFlushOpt.isPresent()) {
                List<Card> straightFlushCards = straightFlushOpt.get();
                return Optional.of(new BasePokerHand(HandRankEnum.STRAIGHT_FLUSH, straightFlushCards));
            }
        }

        return Optional.empty();
    }
}
