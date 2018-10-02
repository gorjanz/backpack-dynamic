package edu.gorjanz.knapsack.service;

import edu.gorjanz.knapsack.domain.PackingCombination;

public interface Solver {

    public String solve(PackingCombination itemsCombination);
}
