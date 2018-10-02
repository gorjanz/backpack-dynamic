package edu.gorjanz.knapsack.service;


import edu.gorjanz.knapsack.domain.PackingCombination;
import edu.gorjanz.knapsack.util.ParsingUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DPSolverTest {

    @Test
    public void shouldSolveWithTwoDecimalContinuousWeights() throws Exception {
        String line1 = "81 : (1,53,€45) (2,88,€98) (3,78,€3) (4,72,€76) (5,30,€9) (6,46,€48)";
        String line2 = "75 : (1,85,€29) (2,14,€74) (3,39,€16) (4,26,€55) (5,63,€52) (6,76,€75) (7,60,€74) (8,93,€35) (9,89,€78)";
        String line3 = "56 : (1,90,€13) (2,33,€40) (3,43,€10) (4,37,€16) (5,46,€36) (6,48,€79) (7,81,€45) (8,19,€79) (9,67,€64)";
        String line4 = "8 : (1,15,€34)";

        PackingCombination testCase1 = ParsingUtils.parseCombination(line1);
        PackingCombination testCase2 = ParsingUtils.parseCombination(line2);
        PackingCombination testCase3 = ParsingUtils.parseCombination(line3);
        PackingCombination testCase4 = ParsingUtils.parseCombination(line4);

        Solver solver = new DPSolver();
        Assert.assertEquals("4", solver.solve(testCase1));
        Assert.assertEquals("2,7", solver.solve(testCase2));
        Assert.assertEquals("2,8", solver.solve(testCase3));
        Assert.assertEquals("-", solver.solve(testCase4));
    }
}
