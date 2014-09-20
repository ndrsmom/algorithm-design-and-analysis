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

    def partition_helper(self, array, length, pivot, min_idx, max_idx):
        self.assertEqual(length, len(array))
        self.assertEqual(0, len(filter(lambda x: x > pivot, array[min_idx:pivot])))
        self.assertEqual(0, len(filter(lambda y: y < pivot, array[pivot:max_idx])))

    def test_partition_full(self):
        array = [9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0]
        pivot = 8
        init_len = len(array)
        Quicksort.partition(array, pivot, 0, len(array))
        self.partition_helper(array, init_len, pivot, 0, len(array))

    def test_partition_subset(self):
        array = [9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0]
        pivot = 4
        init_len = len(array)
        Quicksort.partition(array, pivot, 0, 8)
        self.partition_helper(array, init_len, pivot, 0, 8)