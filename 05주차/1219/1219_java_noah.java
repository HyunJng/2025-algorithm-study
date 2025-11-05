import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
    }

    static int N, start, end, M;
    static List<Edge> edges;
    static long[] money;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, w));
        }

        money = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) money[i] = Long.parseLong(st.nextToken());

        long[] dist = new long[N];
        boolean[] reachable = new boolean[N];
        Arrays.fill(dist, Long.MIN_VALUE);
        dist[start] = money[start];
        reachable[start] = true;

        for (int i = 0; i < N - 1; i++) {
            for (Edge e : edges) {
                if (dist[e.u] == Long.MIN_VALUE) continue;
                long cand = dist[e.u] - e.w + money[e.v];
                if (cand > dist[e.v]) {
                    dist[e.v] = cand;
                    reachable[e.v] = true;
                }
            }
        }

        boolean[] affected = new boolean[N];
        for (Edge e : edges) {
            if (dist[e.u] != Long.MIN_VALUE && dist[e.u] - e.w + money[e.v] > dist[e.v]) {
                affected[e.v] = true;
                affected[e.u] = true;
            }
        }

        if (canReach(affected, edges, end)) {
            System.out.println("Gee");
            return;
        }

        if (!reachable[end] || dist[end] == Long.MIN_VALUE) {
            System.out.println("gg");
        } else {
            System.out.println(dist[end]);
        }
    }

    static boolean canReach(boolean[] affected, List<Edge> edges, int end) {
        boolean[] visited = new boolean[affected.length];
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < affected.length; i++) if (affected[i]) { visited[i] = true; q.add(i); }

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == end) return true;
            for (Edge e : edges) {
                if (e.u == cur && !visited[e.v]) {
                    visited[e.v] = true;
                    q.add(e.v);
                }
            }
        }
        return false;
    }
}
