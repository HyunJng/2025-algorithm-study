import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static class Node {
        private String name;
        private Map<String, Node> children = new HashMap<>();

        public Node(String name) {
            this.name = name;
        }

        public Node getOrCreate(String name) {
            return children.computeIfAbsent(name, Node::new);
        }

    }

    public static final Node root = new Node("");

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            String[] names = new String[k];
            for (int j = 0; j < k; j++) {
                names[j] = st.nextToken();
            }
            addNodes(names);
        }

        bw.write(printNodes());

        bw.flush();
        br.close();
        bw.close();
    }

    public static void addNodes(String[] names) {
        Node cur = root;
        for (String name : names) {
            cur = cur.getOrCreate(name);
        }
    }

    public static String printNodes() {
        StringBuilder sb = new StringBuilder();
        print(0, root, sb);
        return sb.toString();
    }

    public static void print(int depth, Node node, StringBuilder sb) {

        List<String> keys = new ArrayList<>(node.children.keySet());
        Collections.sort(keys);

        for (String key : keys) {
            for (int i = 0; i < depth; i++) {
                sb.append("--");
            }
            sb.append(key).append('\n');
            print(depth + 1, node.children.get(key), sb);
        }
    }
}