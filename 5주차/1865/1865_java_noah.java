import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int from, to, time;
        Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, t));
                edges.add(new Edge(e, s, t));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, -t));
            }

            if (hasNegativeCycle(edges, N)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static boolean hasNegativeCycle(List<Edge> edges, int N) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, 0);

        for (int i = 1; i <= N; i++) {
            boolean updated = false;
            for (Edge e : edges) {
                if (dist[e.to] > dist[e.from] + e.time) {
                    dist[e.to] = dist[e.from] + e.time;
                    updated = true;
                    if (i == N) return true;
                }
            }
            if (!updated) break;
        }
        return false;
    }
}
