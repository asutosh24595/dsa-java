/**
 * Valid Anagram
 * Pattern: counting / hashing
 * Approach:  int[26]; +1 for s, -1 for t in one loop; all zeros = anagram
 * Time: O(n)  Space: (1) 
 * Alternatives: sort both strings and compare, O(n log n)
 * Mistake I made:  returned true on the first zero slot instead of checking all 26
 */

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if(c != 0){
                return false;
            }
        }

        return true;
    }
}
