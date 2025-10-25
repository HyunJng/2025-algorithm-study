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

    private static int n, m;
    private static List<Edge> graph;
    private static int[] distance;
    private static int[] path;

    public boolean bellmanFord(int start) {

        Arrays.fill(distance, Integer.MIN_VALUE);
        Arrays.fill(path, -1);
        distance[start] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                Edge edge = graph.get(j);

                if (distance[edge.u] != Integer.MIN_VALUE && distance[edge.v] < distance[edge.u] + edge.cost) {
                    distance[edge.v] = distance[edge.u] + edge.cost;
                    path[edge.v] = edge.u;
                }
            }
        }

        for (Edge edge : graph) {
            if (distance[edge.u] != Integer.MIN_VALUE && distance[edge.v] < distance[edge.u] + edge.cost) {
                if (isCycleConnected(edge.v)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCycleConnected(int from) {

        boolean[] visited = new boolean[n + 1];
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

        return visited[n];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main main = new Main();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        distance = new int[n + 1];
        path = new int[n + 1];
        graph = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.add(new Edge(u, v, w));
        }

        StringBuilder answer = new StringBuilder("-1");

        if (!main.bellmanFord(1) && distance[n] != Integer.MIN_VALUE) {
            answer = new StringBuilder();
            int current = n;
            while (current != -1) {
                answer.insert(0, current + " ");
                current = path[current];
            }
        }

        bw.write(answer.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}