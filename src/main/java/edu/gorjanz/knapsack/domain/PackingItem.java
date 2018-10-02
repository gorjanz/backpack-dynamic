package edu.gorjanz.knapsack.domain;

public class PackingItem implements Comparable<PackingItem> {

    private int index;
    private int weight;
    private int value;

    public PackingItem() {}

    public PackingItem(int index, int weight, int value) {
        this.index = index;
        this.weight = weight;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(PackingItem o) {
        if (this.getWeight() == o.getWeight()) {
            return 0;
        }

        return this.getWeight() - o.getWeight();
    }

    @Override
    public String toString() {
        return "{" + index + weight + value + '}';
    }
}
