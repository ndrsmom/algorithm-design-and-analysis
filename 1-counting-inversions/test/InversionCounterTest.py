import unittest
import InversionCounter


class InversionCounterTest(unittest.TestCase):
    """Tests for InversionCounter.py"""

    def setUp(self):
        self.path = "../../resources/test/"

    # Acceptance Tests

    def test_empty_list(self):
        count = InversionCounter.count_inversions(self.path + "SortTest1.txt")
        self.assertEqual(0, count)

    def test_single_elt_list(self):
        count = InversionCounter.count_inversions(self.path + "SortTest2.txt")
        self.assertEqual(0, count)

    def test_inorder_list(self):
        count = InversionCounter.count_inversions(self.path + "SortTest3.txt")
        self.assertEqual(0, count)

    def test_backwards_list(self):
        count = InversionCounter.count_inversions(self.path + "SortTest4.txt")
        self.assertEqual(15, count)

    # Unit Tests

    def test_recursion_mixed_order_list(self):
        array = [9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0]
        sorted_list = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
        result, count = InversionCounter.recursive_sort_and_count(array)
        self.assertEqual(56, count)
        self.assertEqual(sorted_list, result)

    def test_merge_mixed_order_list(self):
        left = [0, 2, 4]
        right = [1, 3, 5]
        sorted_list = [0, 1, 2, 3, 4, 5]

        merged, count = InversionCounter.merge_and_count_split(left, right)
        self.assertEqual(3, count)
        self.assertEqual(sorted_list, merged)

if __name__ == '__main__':
    unittest.main()
