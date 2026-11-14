import java.util.HashSet;
import java.util.Set;

/**
 * Contains Duplicate (LeetCode 217)
 * Pattern: hashing
 * Approach: add each number to a HashSet; add() returns false on a repeat.
 * Time: O(n)  Space: O(n)
 * Alternatives: brute force O(n^2) time, O(1) space;
 *               sort + check neighbours O(n log n)
 * Mistake I made: first wrote the nested loop and thought it was O(n).
 */
class ContainsDuplicate {
    public boolean hasDuplicate(int[] nums) {
        Set<Integer> duplicate = new HashSet<Integer>();
        for (int num : nums){
            if(!duplicate.add(num)){
                return true;
            }
        }
        return false;
    }
}