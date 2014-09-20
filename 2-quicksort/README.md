QuickSort
=========

As with the counting inversions problem, we have an input file `IntegerArray.txt` that contains all of the 100,000 integers between 1 and 100,000 (inclusive) in some order, with no integer repeated. This is an input array where the ith row of the file indicates the ith entry of an array.

The goal is to sort the array in-place (after reading into memory) using the QuickSort algorithm.

## Motivation

Besides being a good algorithm, Quicksort is very elegant and has an interesting element of randomness.

## Algorithm

**<a href="http://www.youtube.com/watch?v=n67RYI_0sc0" target="_blank">PIVOT!!!</a>**

This is another divide and conquer algorithm. Naturally for this kind of problem it makes sense to use recursion but the algorithm works much differently than Mergesort. Instead of breaking down the problem with recursion and then combining the resulting pieces, we will first do some work and then repeat on subsets of the problem with recursion.

The key idea behind Quicksort is to **partition** the array around some selected **pivot** element. First pick some element to be the pivot element. The array will then be arranged by iterating through and swapping such that all elements to the left of the pivot are less than the pivot value and all elements to the right of the pivot are greater than the pivot value. This enforces some ordering on the array, although it does not necessarily sort it. The array is then split using the pivot to partition and the subarrays are similarly partitioned through recursion. Eventually the array is fully sorted. Since the elements are swapped, this all happens in-place.

## Performance

The running time depends **crucially** on which pivots are chosen!

Consider the way this algorithm works. Each partition step will iterate through the entire array. This means the performance is O(N * the # of partition steps).

How do we know how many partitions there will be? It depends on how well the array gets sorted with each partition, which depends on the pivot element that is chosen each time.

Consider if we implemented an algorithm that always picks the first element of the subarray for the pivot. Imagine an array that is already sorted. (Initial order does not affect the performance but it's easy to picture this case). Each element would be chosen as a pivot. And since the partitioning takes O(N) time, the total performance cost would be... O(N<sup>2</sup>)!

Well, what would we pick as the pivot in the best case? The element with the median value would partition the array evenly in two each time. Then we would only need to use O(logN) elements as pivots to process the whole array. The total performance cost would be O(NlogN), which is great.

But there is a problem. How do we pick the median valued element if the array isn't sorted? Unfortunately, we can't. But, it turns out that if we pick a random element for each pivot, the array is split more or less in two on average. The performance of each sort will vary a little, but the average running time will be O(NlogN). How convenient!

**Theorem: For every input array of length N, the average running time of Quicksort with 
random pivots is O(NlogN).**

Note that this holds for EVERY input, with no assumptions on the data! This theorem can be proved using some concepts from probability theory (which I'm not going to do here).
