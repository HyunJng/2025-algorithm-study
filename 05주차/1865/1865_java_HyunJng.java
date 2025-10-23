import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    public static int n;
    public static List<Edge> list;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            bw.append(solution(n, m, w));
            bw.append("\n");
        }

        bw.flush();
        bw.close();
    }

    public static String solution(int n, int m, int w) throws Exception {
        for (int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            list.add(new Edge(start, end, value));
            list.add(new Edge(end, start, value));
        }

        for (int i = 1; i <= w; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            list.add(new Edge(start, end, value * -1));
        }

        int[] weight = new int[n + 1];
        Arrays.fill(weight, 0);

        for (int j = 1; j < 2 * m + w; j++) {
            boolean changed = false;
            for (Edge e : list) {
                if (weight[e.start] + e.weight < weight[e.end]) {
                    weight[e.end] = weight[e.start] + e.weight;
                    changed = true;
                }
            }
            if(!changed) break;
        }

        for (Edge e : list) {
            if (weight[e.start] + e.weight < weight[e.end]) {
                return "YES";
            }
        }

        return "NO";
    }

    public static class Edge {
        int start, end, weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}