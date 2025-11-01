import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static int n, m;
    static List<Edge> edges;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        readInput();

        long[] dist = new long[n + 1];
        boolean[] reachable = new boolean[n + 1];
        int[] prev = new int[n + 1];

        boolean hasInfinite = bellmanFord(dist, reachable, prev);

        if (hasInfinite || !reachable[n]) {
            System.out.println(-1);
        } else {
            printPath(prev, n);
        }
    }

    static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, w));
            graph.get(u).add(v);
        }
    }

    static boolean bellmanFord(long[] dist, boolean[] reachable, int[] prev) {
        for (int i = 1; i <= n; i++) {
            dist[i] = 0;
            reachable[i] = false;
            prev[i] = -1;
        }
        reachable[1] = true;

        for (int i = 0; i < n - 1; i++) {
            boolean updated = false;
            for (Edge e : edges) {
                if (!reachable[e.u]) continue;
                long cand = dist[e.u] + e.w;
                if (!reachable[e.v] || cand > dist[e.v]) {
                    dist[e.v] = cand;
                    reachable[e.v] = true;
                    prev[e.v] = e.u;
                    updated = true;
                }
            }
            if (!updated) break;
        }

        boolean[] affected = new boolean[n + 1];
        for (Edge e : edges) {
            if (reachable[e.u] && dist[e.u] + e.w > dist[e.v]) {
                affected[e.u] = true;
                affected[e.v] = true;
            }
        }

        return canReach(affected, graph, n);
    }

    static void printPath(int[] prev, int end) {
        List<Integer> path = new ArrayList<>();
        int cur = end;
        while (cur != -1) {
            path.add(cur);
            if (cur == 1) break;
            cur = prev[cur];
        }
        if (path.get(path.size() - 1) != 1) {
            System.out.println(-1);
            return;
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i));
            if (i != 0) System.out.print(" ");
        }
    }

    static boolean canReach(boolean[] affected, List<List<Integer>> graph, int n) {
        boolean[] visited = new boolean[affected.length];
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < affected.length; i++) {
            if (affected[i]) {
                visited[i] = true;
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == n) return true;
            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        return false;
    }
}
