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
