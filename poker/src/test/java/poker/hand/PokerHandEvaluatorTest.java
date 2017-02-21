package poker.hand;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import poker.Deck;
import poker.card.Card;
import poker.card.CardValueEnum;
import poker.evaluation.PokerHandEvaluator;
import poker.helper.PokerHandHelper;

public class PokerHandEvaluatorTest {

    private PokerHandEvaluator evaluator = new PokerHandEvaluator();

    private List<Card> highCardGroup = PokerHandHelper.getHighCard();
    private List<Card> pairGroup = PokerHandHelper.getPair();
    private List<Card> twoPairGroup = PokerHandHelper.getTwoPair();
    private List<Card> threeOfAKindGroup = PokerHandHelper.getThreeOfAKind();
    private List<Card> straightGroup = PokerHandHelper.getStraight();
    private List<Card> flushGroup = PokerHandHelper.getFlush();
    private List<Card> fullHouseGroup = PokerHandHelper.getFullHouse();
    private List<Card> fourOfAKindGroup = PokerHandHelper.getFourOfAKind();
    private List<Card> straightFlushGroup = PokerHandHelper.getStraightFlush();

    @Test
    public void findBestHand_AssignsCorrectRank_HighCard() {
        PokerHand hand = evaluator.findBestHand(highCardGroup);
        assertEquals(HandRankEnum.HIGH_CARD, hand.getGetHandRank());
    }

    @Test
    public void findBestHand_AssignsCorrectRank_Pair() {
        PokerHand hand = evaluator.findBestHand(pairGroup);
        assertEquals(HandRankEnum.PAIR, hand.getGetHandRank());
    }

    @Test
    public void findBestHand_AssignsCorrectRank_TwoPair() {
        PokerHand hand = evaluator.findBestHand(twoPairGroup);
        assertEquals(HandRankEnum.TWO_PAIR, hand.getGetHandRank());
    }

    @Test
    public void findBestHand_AssignsCorrectRank_ThreeOfAKind() {
        PokerHand hand = evaluator.findBestHand(threeOfAKindGroup);
        assertEquals(HandRankEnum.THREE_OF_A_KIND, hand.getGetHandRank());
    }

    @Test
    public void findBestHand_AssignsCorrectRank_Straight() {
        PokerHand hand = evaluator.findBestHand(straightGroup);
        assertEquals(HandRankEnum.STRAIGHT, hand.getGetHandRank());
    }

    @Test
    public void findBestHand_AssignsCorrectRank_Flush() {
        PokerHand hand = evaluator.findBestHand(flushGroup);
        assertEquals(HandRankEnum.FLUSH, hand.getGetHandRank());
    }

    @Test
    public void findBestHand_AssignsCorrectRank_FullHouse() {
        PokerHand hand = evaluator.findBestHand(fullHouseGroup);
        assertEquals(HandRankEnum.FULL_HOUSE, hand.getGetHandRank());
    }

    @Test
    public void findBestHand_AssignsCorrectRank_FourOfAKind() {
        PokerHand hand = evaluator.findBestHand(fourOfAKindGroup);
        assertEquals(HandRankEnum.FOUR_OF_A_KIND, hand.getGetHandRank());
    }

    @Test
    public void findBestHand_AssignsCorrectRank_StraightFlush() {
        PokerHand hand = evaluator.findBestHand(straightFlushGroup);
        assertEquals(HandRankEnum.STRAIGHT_FLUSH, hand.getGetHandRank());
    }

    @Test
    public void findBestHand_GivenCompleteDeck_StraightFlush() {
        PokerHand hand = evaluator.findBestHand(Deck.buildDeck());
        Card topCard = hand.getGetCards().stream().sorted().findFirst().get();
        assertEquals(HandRankEnum.STRAIGHT_FLUSH, hand.getGetHandRank());        
        assertEquals(CardValueEnum.ACE, topCard.getValueType());
    }
}
