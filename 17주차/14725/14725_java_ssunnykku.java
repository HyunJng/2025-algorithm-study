import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int len = Integer.parseInt(br.readLine());
        String[][] arr = new String[len][];
        for (int i = 0; i < len; i++) {
            String[] str = br.readLine().split(" ");
            String[] c = new String[Integer.parseInt(str[0])];
            for (int j = 0; j < Integer.parseInt(str[0]); j++) {
                c[j] = str[j + 1];
            }
            arr[i] = c;
        }

        TrieNode root = new TrieNode();

        for (String[] path : arr) {
            TrieNode current = root;
            for (String c : path) {
                current = current.insert(c);
            }
            current.isEndOfWord = true;
        }
        print(root, 0);

    }

    public static void print(TrieNode node, int depth) {
        node.children.keySet().stream()
                .sorted()
                .forEach(key -> {
                    for (int i = 0; i < depth; i++)
                        System.out.print("--");
                    System.out.println(key);
                    print(node.children.get(key), depth + 1);
                });
    }

}

class TrieNode {
    Map<String, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;

    public TrieNode insert(String word) {
        children.putIfAbsent(word, new TrieNode());
        return children.get(word);
    }

}
