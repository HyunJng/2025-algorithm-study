import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int s;
        int e;
        int t;

        public Edge(int s, int e, int t) {
            this.s = s;
            this.e = e;
            this.t = t;
        }
    }

    private static int tc, n, m, w;
    private static List<Edge> graph;
    private static int[] distance;

    public boolean bellmanFord() {

        Arrays.fill(distance, 0);

        for (int i = 0; i < n; i++) {
            for (Edge edge : graph) {
                if (distance[edge.e] > distance[edge.s] + edge.t) {
                    if (i == n - 1) {
                        return true;
                    }
                    distance[edge.e] = distance[edge.s] + edge.t;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main main = new Main();

        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            distance = new int[n + 1];
            graph = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                graph.add(new Edge(s, e, t));
                graph.add(new Edge(e, s, t));
            }
            for (int j = 0; j < w; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                graph.add(new Edge(s, e, -t));
            }

            sb.append(main.bellmanFord() ? "YES" : "NO").append("\n");
        }

        bw.write(sb.toString());

        bw.flush();
        br.close();
        bw.close();
    }
}
