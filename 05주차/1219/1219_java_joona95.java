import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int u;
        int v;
        int cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

    private static int n, s, e, m;
    private static List<Edge> graph;
    private static long[] distance;
    private static int[] earn;

    public String bellmanFord(int start) {

        Arrays.fill(distance, Integer.MIN_VALUE);
        distance[start] = earn[start];

        boolean isCycle = false;
        for (int i = 0; i < n; i++) {
            for (Edge edge : graph) {
                if (distance[edge.u] != Integer.MIN_VALUE && distance[edge.v] < distance[edge.u]  + earn[edge.v] - edge.cost) {
                    if (i == n - 1 && isCycleConnected(edge.v)) {
                        isCycle = true;
                        break;
                    }
                    distance[edge.v] = distance[edge.u] + earn[edge.v] - edge.cost;
                }
            }
        }

        if (distance[e] == Integer.MIN_VALUE) {
            return "gg";
        }

        if (isCycle) {
            return "Gee";
        }

        return String.valueOf(distance[e]);
    }

    public boolean isCycleConnected(int from) {

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(from);
        visited[from] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (Edge edge : graph) {
                if (edge.u == current && !visited[edge.v]) {
                    visited[edge.v] = true;
                    queue.add(edge.v);
                }
            }
        }

        return visited[e];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main main = new Main();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        distance = new long[n];
        graph = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.add(new Edge(u, v, cost));
        }

        earn = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            earn[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(main.bellmanFord(s));

        bw.flush();
        br.close();
        bw.close();
    }
}