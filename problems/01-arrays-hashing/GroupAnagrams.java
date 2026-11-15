import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> groups = new HashMap<>();

        //Sort method
        //O(m * n logn), Space complexity: O(m * n)
        //m = number of strings, n = length of the longest string


        // for(int i=0; i<strs.length; i++){
        //     char[] chars = strs[i].toCharArray();
        //     Arrays.sort(chars);
        //     String key = new String(chars);

        //     if(!groups.containsKey(key)){
        //         groups.put(key, new ArrayList<>());
        //     }

        //     groups.get(key).add(strs[i]);
        // }

        // couting, hash table
        // O(m * n), Space complexity: O(m * n)
        //m = number of strings, n = length of the longest string
        for (String s: strs){
            int[] count = new int[26];
            for (char c: s.toCharArray()){
                count[c - 'a']++;
            }
            String key = Arrays.toString(count);
            groups.putIfAbsent(key, new ArrayList<>());
            groups.get(key).add(s);
        }
        
        return new ArrayList<>(groups.values());
    }
}
