Counting Inversions
===================

The input file `IntegerArray.txt` contains all of the 100,000 integers between 1 and 100,000 (inclusive) in some order, with no integer repeated.

The goal is to compute the number of inversions. An **inversion** is any pair of array indices such that the value contained in the smaller index of the array is greater than the value contained in the larger index. For example, a sorted array would have zero inversions.

In this case, the ith row of the file indicates the ith entry of an array.

## Motivation

The counting inversions problem is a way to measure numerical similarity between two ranked lists. This is one technique to implement **collaborative filtering**, such as a product recommendation system.

## Algorithm

Because of the large size of this array, we will use a fast divide-and-conquer algorithm.




