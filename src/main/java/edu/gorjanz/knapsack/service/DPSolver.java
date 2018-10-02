package edu.gorjanz.knapsack.service;


import edu.gorjanz.knapsack.domain.Backpack;
import edu.gorjanz.knapsack.domain.PackingCombination;
import edu.gorjanz.knapsack.domain.PackingItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DPSolver implements Solver {

    /**
     * Basic idea behind the algorithm:
     * <p>
     * For each number of items, and for each weight, starting from 0 up till the weight limit:
     * will we get bigger value if we include this item to the items which allow for it's weight or
     * will we get bigger value if don't include this item, and go with the items packed so-far for this weight
     *
     * @param testCase the combination of items
     * @return the indexes of the items that are packed in the final {@link Backpack}.
     * @uses {@link Backpack}
     */
    public String solve(PackingCombination testCase) {
        int weightLimit = testCase.getWeightLimit();
        PackingItem[] items = testCase.getPackingItems().toArray(new PackingItem[]{});
        Arrays.sort(items);
        int numberOfItems = items.length;

        //Create a matrix. Items are in rows and weight at in columns +1 on each side
        Backpack[][] intermediaryValues = new Backpack[numberOfItems + 1][weightLimit + 1];

        for (int row = 0; row <= numberOfItems; row++) {
            for (int col = 0; col <= weightLimit; col++) {
                intermediaryValues[row][col] = new Backpack();
            }
        }

        //What if the knapsack's capacity is 0 -> Set all columns at row 0 to be 0
        for (int col = 0; col <= weightLimit; col++) {
            intermediaryValues[0][col].setValue(0);
        }
        //What if there are no items at home -> Fill the first row with 0
        for (int row = 0; row <= numberOfItems; row++) {
            intermediaryValues[row][0].setValue(0);
        }

        for (int item = 1; item <= numberOfItems; item++) {
            for (int weight = 1; weight <= weightLimit; weight++) {
                //Do the current items weight less than or equal to running weight
                if (items[item - 1].getWeight() <= weight) {
                    int withAdded = items[item - 1].getValue()
                            + intermediaryValues[item - 1][weight - items[item - 1].getWeight()].getValue();
                    int withoutCurrent = intermediaryValues[item - 1][weight].getValue();

                    if (withAdded > withoutCurrent) {
                        intermediaryValues[item][weight].setValue(withAdded);

                        List<Integer> itemIndexes = new ArrayList<>(Optional
                                .ofNullable(intermediaryValues[item - 1][weight - items[item - 1].getWeight()].getItemIndexes())
                                .orElse(new ArrayList<>()));
                        itemIndexes.add(items[item - 1].getIndex());
                        intermediaryValues[item][weight].setItemIndexes(itemIndexes);
                    } else {
                        intermediaryValues[item][weight].setValue(withoutCurrent);
                        intermediaryValues[item][weight].setItemIndexes(intermediaryValues[item - 1][weight].getItemIndexes());
                    }
                } else {
                    //If item's weight is more than the running weight, just carry over the value without the current item
                    intermediaryValues[item][weight].setValue(intermediaryValues[item - 1][weight].getValue());
                    intermediaryValues[item][weight].setItemIndexes(intermediaryValues[item - 1][weight].getItemIndexes());
                }
            }
        }

        return intermediaryValues[numberOfItems][weightLimit].getItemsIndices();
    }
}
