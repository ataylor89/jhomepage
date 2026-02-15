package homepage.algorithm;

import java.util.Map;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class Rot13 {
    
    private Map<Character, Character> lookupTable;

    public Rot13() {
        lookupTable = new HashMap<>();
        populateTable();
    }

    private void populateTable() {
        for (int i = 0; i < 26; i++) {
            int sourceCodePoint = (int) 'a' + i;
            int targetCodePoint = (int) 'a' + (i + 13) % 26;
            lookupTable.put((char) sourceCodePoint, (char) targetCodePoint);
            sourceCodePoint = (int) 'A' + i;
            targetCodePoint = (int) 'A' + (i + 13) % 26;
            lookupTable.put((char) sourceCodePoint, (char) targetCodePoint);
        }
    }

    public String rot13(String message) {
        String result = "";
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if (lookupTable.containsKey(ch)) {
                char substitute = lookupTable.get(ch);
                result += substitute;
            }
            else {
                result += ch;
            }
        }
        return result;
    }

}
