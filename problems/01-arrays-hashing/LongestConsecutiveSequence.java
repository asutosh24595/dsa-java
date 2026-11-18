/**
 * Longest Consecutive Sequence 
 * Pattern: hashing - HashSet for O(1) membership; count runs only from their start
 * Approach: put all numbers in a set (duplicates collapse automatically).
 *           For each number x in the set: if x - 1 is NOT in the set, x starts a run.
 *           From each start, count up while x + 1, x + 2, ... are in the set.
 *           Keep the best with longest = Math.max(longest, length).
 * Time: O(n) - the outer loop visits each value once; the inner while only runs from
 *       run starts, and runs don't overlap, so it steps through each value at most
 *       once in total (mailroom: shared work adds, doesn't multiply)
 * Space: O(n) for the set
 * Alternatives: sort, then walk counting runs (skip duplicates) - O(n log n), O(1) extra;
 *               map from run boundary to run length, merging runs - O(n) but more complex
 * Notes:
 *   - loop over the set, not nums: with many duplicates, nums would recount the same run
 *   - without the start check it becomes O(n^2) (recounting from every number in a run)
 *   - best = Math.max(best, candidate), never best = candidate
 *   - HashSet has no meaningful order; iteration follows bucket order
 */

import java.util.HashSet;
import java.util.Set;

class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            seen.add(num);
        }

        int longest = 0;

        for( int num : seen){
            if(!seen.contains(num-1)){
                int current = num;
                int length = 1;

                while(seen.contains(current + 1)){
                    length++;
                    current++;
                }

                longest = Math.max(longest,length);
            }
        }

        return longest;
    }
}
