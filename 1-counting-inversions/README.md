Counting Inversions
===================

The input file `IntegerArray.txt` contains all of the 100,000 integers between 1 and 100,000 (inclusive) in some order, with no integer repeated.

The goal is to compute the number of inversions. An **inversion** is any pair of array indices such that the value contained in the smaller index of the array is greater than the value contained in the larger index. For example, a sorted array would have zero inversions.

In this case, the ith row of the file indicates the ith entry of an array.

## Motivation

The counting inversions problem is a way to measure numerical similarity between two ranked lists. This is one technique to implement **collaborative filtering**, such as a product recommendation system.

## Algorithm

Because of the large size of this array, we will use a fast divide-and-conquer algorithm.

Let's break this down into different types of inversions:

- **Left** inversion: If both indices <= n/2
- **Right** inversion: If both indices > n/2
- **Split** inversion: If one index <= n/2 and the other is > n/2

The left and right inversions can be counted **recursively**. The split inversions will have to be counted separately. The recursion divides the array by 2 with each step, so the total cost of the recursive portion is O(logN). We should aim to count the split inversions in linear time so that the entire algorithm only costs O(NlogN).

We can accomplish this by having the recursive steps **sort** in addition to counting inversions. This will naturally uncover any split inversions.


