/**
 * Merge Sort
 * Idea: split in half until single elements (already sorted), then merge back up.
 *       sort() only splits; merge() does all the comparing.
 *       merge() only works on sorted inputs - splitting to singles guarantees that.
 *
 * merge(left, right): two pointers, take the smaller front each step, then copy leftovers.
 *                     O(a + b). Use <= to keep equal elements in order (stable).
 * sort(arr): base case length <= 1; split at mid; left = sort(left); right = sort(right);
 *            return merge(left, right).
 *
 * Time: O(n log n) in every case - halving gives log n levels, merge gives n work per level.
 *       Recurrence: T(n) = 2T(n/2) + n
 * Space: O(n) for the merge arrays (+ O(log n) recursion stack)
 *
 * vs Arrays.sort: primitives use Dual-Pivot Quicksort; objects use TimSort (merge-sort based, stable).
 */

package algorithms;

import java.util.Arrays;

class MergeSort {
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    public static int[] sort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int mid = arr.length / 2;

        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        left = sort(left);
        right = sort(right);

        return merge(left, right);

    }

    public static void main(String[] args) {
        int[] merged = merge(new int[] { 1, 2, 3, 4, 5, 10, 12 }, new int[] { 6, 11, 50, 60, 70 });
        System.out.println(java.util.Arrays.toString(merged));
        System.out.println(java.util.Arrays.toString(sort(new int[] { 5, 2, 8, 1, 9, 3 })));
    }
}