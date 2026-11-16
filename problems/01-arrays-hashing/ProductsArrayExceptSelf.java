/**
 * Product of Array Except Self
 * Pattern: prefix / suffix products (two passes)
 * Approach: answer[i] = (product of everything left of i) x (product of everything right of i).
 *           Pass 1 (left to right): result[i] = product of all elements before i.
 *           Pass 2 (right to left): multiply result[i] by a running rightProduct,
 *           then fold nums[i] into rightProduct for the next index.
 * Time: O(n)  Space: O(1) extra (output excluded); two separate passes add, not multiply
 * Alternatives: total product / nums[i] - forbidden by the problem, and breaks on zeros
 *               (0 / 0 crashes); separate left[] and right[] arrays - O(n) extra space
 * Note: test with inputs that would expose bugs (no 1s at the start, distinct values),
 *       then edge cases: a zero, two zeros, negatives
 */

class ProductsArrayExceptSelf {
    
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int rightProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return result;
    }

}
