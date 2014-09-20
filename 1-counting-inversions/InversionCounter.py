def main():
    filename = "../resources/IntegerArray.txt"
    count = count_inversions(filename)
    print "Inversions:", count


def count_inversions(filename):
    sorted_array, count = recursive_sort_and_count(parse_input(filename))
    return count


def parse_input(filename):
    """Parse the array of integers from a file."""
    with open(filename) as f:
        integer_array = [int(line.strip()) for line in f]
    return integer_array


def recursive_sort_and_count(array):
    """Recursively compute the number of inversions via sorting."""
    length = len(array)

    # base case
    if length <= 1:
        return array, 0
    else:
        mid = length / 2
        left, left_count = recursive_sort_and_count(array[0:mid])
        right, right_count = recursive_sort_and_count(array[mid:length])
        merged, split_count = merge_and_count_split(left, right)
        return merged, left_count + right_count + split_count


def merge_and_count_split(left, right):
    """Compute the split inversions by merging the sorted arrays."""
    merged = []
    count, i, j = 0, 0, 0
    while i < len(left) and j < len(right):
        if left[i] < right[j]:
            merged.append(left[i])
            i += 1
        else:
            merged.append(right[j])
            j += 1

            # increment inversion count by number of elements remaining in left
            # because all remaining are less than right[j]
            count += len(left) - i

    merged += left[i:]
    merged += right[j:]
    return merged, count


if __name__ == "__main__": main()