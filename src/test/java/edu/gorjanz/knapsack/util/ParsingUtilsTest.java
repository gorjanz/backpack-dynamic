package edu.gorjanz.knapsack.util;

import edu.gorjanz.knapsack.domain.PackingCombination;
import edu.gorjanz.knapsack.exception.APIException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ParsingUtilsTest {

    @Test
    public void shouldParseTestCaseCorrectly() throws Exception {
        String combination1 = "81 : (1,53,€45) (2,88,€98) (3,78,€3) (4,72,€76) (5,30,€9) (6,46,€48)";
        String combination2 = "75 : (1,85,€29) (2,14,€74) (3,39,€16) (4,26,€55) (5,63,€52) (6,76,€75) (7,60,€74) (8,93,€35) (9,89,€78)";
        String combination3 = "56 : (1,90,€13) (2,33,€40) (3,43,€10) (4,37,€16) (5,46,€36) (6,48,€79) (7,81,€45) (8,19,€79) (9,67,€64)";

        PackingCombination parsed1 = ParsingUtils.parseCombination(combination1);
        PackingCombination parsed2 = ParsingUtils.parseCombination(combination2);
        PackingCombination parsed3 = ParsingUtils.parseCombination(combination3);

        Assert.assertEquals(81, parsed1.getWeightLimit());
        Assert.assertEquals(75, parsed2.getWeightLimit());
        Assert.assertEquals(56, parsed3.getWeightLimit());

        Assert.assertEquals(6, parsed1.getPackingItems().size());
        Assert.assertEquals(9, parsed2.getPackingItems().size());
        Assert.assertEquals(9, parsed3.getPackingItems().size());

        Assert.assertEquals(1, parsed1.getPackingItems().get(0).getIndex());
        Assert.assertEquals(74, parsed2.getPackingItems().get(1).getValue());
        Assert.assertEquals(43, parsed3.getPackingItems().get(2).getWeight());
    }

    @Test(expected = APIException.class)
    public void shouldThrowDueToLackOfWeightLimit() throws Exception {
        String combination1 = " : (1,53,€45) (2,88,€98) (3,78,€3) (4,72,€76) (5,30,€9) (6,46,€48)";

        PackingCombination parsed1 = ParsingUtils.parseCombination(combination1);
    }

    @Test(expected = APIException.class)
    public void shouldThrowDueToMissingValue() throws Exception {
        String combination1 = "81 : (1,53,) (2,88,€98) (3,78,€3) (4,72,€76) (5,30,€9) (6,46,€48)";

        PackingCombination parsed1 = ParsingUtils.parseCombination(combination1);
    }

    @Test(expected = APIException.class)
    public void shouldThrowDueToMissingParentheses() throws Exception {
        String combination1 = "81 : (1,53,€45) (2,88,€98 (3,78,€3) (4,72,€76) (5,30,€9) (6,46,€48)";

        PackingCombination parsed1 = ParsingUtils.parseCombination(combination1);
    }
}
