import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> group = new HashMap<>();

        // Sort method Time Complexity O(n log n), Space Complexity O(n)
        // for (int num : nums) {
        // group.put(num, group.getOrDefault(num, 0) + 1);
        // }

        // List<Map.Entry<Integer, Integer>> entries = new
        // ArrayList<>(group.entrySet());

        // entries.sort((a, b) -> b.getValue() - a.getValue());
        // int[] result = new int[k];
        // for (int i = 0; i < k; i++) {
        // result[i] = entries.get(i).getKey();
        // }

        // bucket sort Time Complexity: O(n), Space Complexity: O(n)
        // Three words: Count, Bucket, Walk back.

        // Count: count how often each number appears.
        // map.put(n, map.getOrDefault(n, 0) + 1)
        // Bucket: put each number into the slot for its count.
        // freq[count].add(number) in an array of lists of size n + 1
        // Walk back: go from the highest count down, collecting numbers until you have k.
        // for (i = n; i > 0; i--), and return once index == k
         
        List<Integer>[] freq = new List[nums.length + 1];

        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        for (int num : nums) {
            group.put(num, group.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : group.entrySet()) {
            freq[entry.getValue()].add(entry.getKey());
        }

        int[] result = new int[k];

        int index = 0;

        for (int i = freq.length - 1; i > 0; i--) {
            for (int n : freq[i]) {
                result[index++] = n;
                if (index == k) {
                    return result;
                }
            }
        }

        return result;

    }
}
