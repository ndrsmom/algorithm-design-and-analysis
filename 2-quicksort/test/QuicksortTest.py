import unittest
import Quicksort


class QuicksortTest(unittest.TestCase):
    """Tests for Quicksort.py"""

    # Acceptance Tests
    # TODO

    # Unit Tests

    def test_quicksort_mixed_order_list(self):
        array = [9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0]
        sorted_list = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
        result = Quicksort.quicksort(array)
        self.assertEqual(sorted_list, result)

    def check_partition_valid(self, array, length, pivot, min_idx, max_idx):
        pivot_idx = array.index(pivot)
        self.assertEqual(length, len(array))
        self.assertEqual(0, len(filter(lambda x: x > pivot, array[min_idx:pivot_idx])))
        self.assertEqual(0, len(filter(lambda y: y < pivot, array[pivot_idx:max_idx])))

    def test_partition_full(self):
        array = [9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0]
        pivot = 8
        pivot_idx = array.index(pivot)
        init_len = len(array)
        Quicksort.partition_array(array, pivot_idx, 0, init_len)
        self.check_partition_valid(array, init_len, pivot, 0, init_len)
        self.assertEqual(pivot, array[pivot])

    def test_partition_subset(self):
        array = [9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0]
        pivot = 6
        pivot_idx = array.index(pivot)
        init_len = len(array)
        Quicksort.partition_array(array, pivot_idx, 2, 8)
        self.check_partition_valid(array, init_len, pivot, 2, 8)

    def test_partition_partial_subset_left(self):
        array = [9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0]
        pivot = 8
        pivot_idx = array.index(pivot)
        init_len = len(array)
        Quicksort.partition_array(array, pivot_idx, 0, 8)
        self.check_partition_valid(array, init_len, pivot, 0, 8)

    def test_partition_partial_subset_right(self):
        array = [9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0]
        pivot = 6
        pivot_idx = array.index(pivot)
        init_len = len(array)
        Quicksort.partition_array(array, pivot_idx, 2, init_len)
        self.check_partition_valid(array, init_len, pivot, 2, init_len)

    def test_partition_with_dups(self):
        array = [9, 12, 3, 2, 1, 6, 8, 2, 6]
        pivot = 6
        pivot_idx = array.index(pivot)
        init_len = len(array)
        Quicksort.partition_array(array, pivot_idx, 0, init_len)
        self.check_partition_valid(array, init_len, pivot, 0, init_len)

    def test_partition_min_elt_pivot(self):
        array = [9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0]
        pivot = 0
        pivot_idx = array.index(pivot)
        init_len = len(array)
        Quicksort.partition_array(array, pivot_idx, 0, init_len)
        self.check_partition_valid(array, init_len, pivot, 0, init_len)

    def test_partition_max_elt_pivot(self):
        array = [9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0]
        pivot = 14
        pivot_idx = array.index(pivot)
        init_len = len(array)
        Quicksort.partition_array(array, pivot_idx, 0, init_len)
        self.check_partition_valid(array, init_len, pivot, 0, init_len)