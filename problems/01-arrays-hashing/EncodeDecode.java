//Time Complexity: O(n+m), Space Complexity: O(n+m) where n = total characters, m = number of strings

import java.util.ArrayList;
import java.util.List;

class EncodeDecode {
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append('#').append(str);
        }

        return sb.toString();
    }

    public List<String> decode(String str) {
        List<String> resultList = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int j = i;
            while (str.charAt(j) != '#') {
                j++;
            }
            int length = Integer.parseInt(str.substring(i, j));
            String word = str.substring(j + 1, j + 1 + length);
            resultList.add(word);
            i = j + 1 + length;
        }

        return resultList;
    }
}
