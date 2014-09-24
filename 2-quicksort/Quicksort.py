# def main():
#     filename = "../resources/IntegerArray.txt"
#     result = "IntegerArraySorted.txt"
#     sort(filename)
#
# def sort(filename):
#
# def write_output(sorted):
#     out_file = "IntegerArraySorted.txt"
#     with open(out_file, "w") as f:


def quicksort(array):
    return None


def partition_array(array, pivot_idx, min_idx, max_idx):
    pivot = array[pivot_idx]
    # move pivot elt to leftmost idx for now
    array[min_idx], array[pivot_idx] = array[pivot_idx], array[min_idx]

    # MUST maintain partitioned section:
    # all elts A[min_idx + 1] to A[partition-1] < pivot and
    # all elts A[partition] to A[current-1] > pivot.
    partition = min_idx + 1
    current = min_idx + 1

    while current < max_idx:
        if array[current] < pivot:
            # rearrange so that maintain a partitioned section
            array[partition], array[current] = array[current], array[partition]
            partition += 1
        current += 1

    array[min_idx], array[partition-1] = array[partition-1], array[min_idx]