package poker.hand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import poker.card.Card;
import poker.evaluation.StraightEvaluator;
import poker.helper.PokerHandHelper;

public class StraightEvaluatorTest {

    private StraightEvaluator evaluator = new StraightEvaluator();

    private List<Card> topStraight = PokerHandHelper.getTopStraight();
    private List<Card> middleStraight = PokerHandHelper.getStraight();
    private List<Card> bottomStraight = PokerHandHelper.getBottomStraight();
    private List<Card> partialStraight = PokerHandHelper.getPartialStraight();
    private List<Card> wrapAroundStraight = PokerHandHelper.getWrapAroundStraight();

    private int straightLen = 5;

    @Test
    public void findStraight_TopStraight_IsFound() {
        Optional<List<Card>> straightOpt = evaluator.findStraight(topStraight, straightLen);
        assertTrue(straightOpt.isPresent());
    }

    @Test
    public void findStraight_MiddleStraight_IsFound() {
        Optional<List<Card>> straightOpt = evaluator.findStraight(middleStraight, straightLen);
        assertTrue(straightOpt.isPresent());
    }

    @Test
    public void findStraight_BottomStraight_IsFound() {
        Optional<List<Card>> straightOpt = evaluator.findStraight(bottomStraight, straightLen);
        assertTrue(straightOpt.isPresent());
    }

    @Test
    public void findStraight_PartialStraight_IsNotFound() {
        Optional<List<Card>> straightOpt = evaluator.findStraight(partialStraight, straightLen);
        assertFalse(straightOpt.isPresent());
    }

    @Test
    public void findStraight_WrapAroundStraight_IsNotFound() {
        Optional<List<Card>> straightOpt = evaluator.findStraight(wrapAroundStraight, straightLen);
        assertFalse(straightOpt.isPresent());
    }

    @Test
    public void findStraight_IsFound_HasProvidedLength() {
        Optional<List<Card>> straightOpt = evaluator.findStraight(middleStraight, straightLen);
        assertEquals(straightLen, straightOpt.get().size());
    }

    @Test
    public void findStraight_AbnormalLength_IsFound_HasProvidedLength() {
        int testLen = straightLen - 2;
        Optional<List<Card>> straightOpt = evaluator.findStraight(middleStraight, testLen);
        assertEquals(testLen, straightOpt.get().size());
    }
}
