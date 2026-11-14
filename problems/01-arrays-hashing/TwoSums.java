/**
 * Two Sums
 * Pattern: hashing (complement lookup)
 * Approach:  for each nums[i], check if target - nums[i] is already in a
 *           value -> index map; if yes, return both indices; else store nums[i]
 * Time: O(n)  Space: O(n) 
 * Alternatives: nested loops O(n^2) time, O(1) space;
 *               sort + two pointers O(n log n), but sorting loses original indices
 * Mistake I made:  used JS array syntax [i, j] instead of new int[]{i, j};
 *                 forgot map.put(); must check before put so an element
 *                 doesn't match itself
 */

import java.util.HashMap;
import java.util.Map;

public class TwoSums {
     public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int required = target - nums[i];
            if(map.containsKey(required)){
                return new int[] {map.get(required),i};
            }

            map.put(nums[i],i);
        }

        return new int[0];
    }
}
