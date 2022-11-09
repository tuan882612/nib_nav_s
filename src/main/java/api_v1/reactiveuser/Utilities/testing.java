package api_v1.reactiveuser.Utilities;

import java.util.LinkedHashMap;
import java.util.Map;

public class testing {
    public static void main(String[] args) {
        String str = "I will arrive at Eden Academy really soon"
                .replaceAll(" ","").toLowerCase();

        StringBuilder res = new StringBuilder();
        LinkedHashMap<Character, Integer> hash = new LinkedHashMap<>();

        for (Character let : str.toCharArray()) {
            if (hash.containsKey(let)) {
                hash.put(let, hash.get(let)+1);
            } else {
                hash.put(let, 1);
            }
        }

        for (Map.Entry<Character, Integer> set : hash.entrySet()) {
            res.append(set.getValue().toString()).append(set.getKey());
        }

        System.out.println(res);
    }
}
