import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int n, m, r;
    private static int[] items;
    private static int[] dist;
    private static List<Node>[] nodes;

    public static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        nodes = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new LinkedList<>();
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            nodes[a].add(new Node(b, l));
            nodes[b].add(new Node(a, l));
        }

        bw.write(String.valueOf(solution()));

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution() {

        int answer = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            dist = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                dist[j] = Integer.MAX_VALUE;
            }
            pq.add(new Node(i, 0));
            dist[i] = 0;

            while (!pq.isEmpty()) {
                Node node = pq.poll();

                for (Node next : nodes[node.to]) {
                    if (dist[next.to] > dist[node.to] + next.cost) {
                        dist[next.to] = dist[node.to] + next.cost;
                        pq.add(new Node(next.to, dist[node.to] + next.cost));
                    }
                }
            }

            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[j] <= m) {
                    sum += items[j];
                }
            }
            answer = Math.max(answer, sum);
        }

        return answer;
    }
}