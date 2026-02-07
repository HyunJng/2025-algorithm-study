import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static List<Node>[] nodes;
    private static int[] dist;
    private static int[] prev;

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

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        nodes = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            nodes[from].add(new Node(to, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        bw.write(solution(start, end));

        bw.flush();
        br.close();
        bw.close();
    }

    public static String solution(int start, int end) {

        StringBuilder answer = new StringBuilder();

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[n + 1];
        prev = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        pq.add(new Node(start, 0));
        dist[start] = 0;
        prev[start] = -1;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[node.to] < node.cost) {
                continue;
            }
            for (Node next : nodes[node.to]) {
                if (dist[next.to] > dist[node.to] + next.cost) {
                    dist[next.to] = dist[node.to] + next.cost;
                    prev[next.to] = node.to;
                    pq.add(new Node(next.to, dist[node.to] + next.cost));
                }
            }
        }

        answer.append(dist[end]).append("\n");

        int count = 0;
        List<Integer> path = new ArrayList<>();
        int idx = end;
        while (idx != -1) {
            count++;
            path.add(idx);
            idx = prev[idx];
        }

        answer.append(count).append("\n");
        for (int i = path.size() - 1; i >= 0; i--) {
            answer.append(path.get(i)).append(" ");
        }

        return answer.toString();
    }
}