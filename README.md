###### Introduction:

A solution to the well-known problem of packing a number of items in a backpack, with total weight less or equal to given weight W, maximizing the total value V they add to the backpack, given a number of items described with their weight and value accordingly.
https://en.wikipedia.org/wiki/Knapsack_problem

In this solution, the indexes of the items that make the final cut are provided at the end.

###### Solution:

Basic premise:
We try to fit as many items, starting from one, to as many as possible, always looking to maximize the value.
At every step we see:
  * will we get bigger value if we include this item to the items which allow for it's weight or
  * will we get bigger value if don't include this item, and go with the items packed so-far for this weight
  
Also:
  * preferring the smallest weight, in case of multiple combinations resulting in same value, meant that we will need to sort the items by weight first (so that we always prefer adding the lighter item)
  * The overhead of backtracking for the indexes, was overcome by using a small OOP design for the intermediary result item (the Backpack class)  
  

###### To run it:

```
mvn clean install
java -jar ./target/packer.jar $(pwd)/src/main/resources/test-cases.txt
```


###### Test cases

Test cases should be provided in the form of:
```
"<weight-limit> : (<index>, <weight>, €<value>) (<index>, <weight>, €<value>) ... (<index>, <weight>, €<value>)"
```
