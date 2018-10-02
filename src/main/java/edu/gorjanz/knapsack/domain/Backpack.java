package edu.gorjanz.knapsack.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Backpack {

    private int value;
    private List<Integer> itemIndexes;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Integer> getItemIndexes() {
        return itemIndexes;
    }

    public void setItemIndexes(List<Integer> itemsIndexes) {
        this.itemIndexes = itemsIndexes;
    }

    public String getItemsIndices() {
        if (value == 0 || itemIndexes == null || itemIndexes.size() == 0) {
            return "-";
        }

        Collections.sort(itemIndexes);
        return String.join(",", itemIndexes.stream().map(String::valueOf).collect(Collectors.toList()));
    }
}
