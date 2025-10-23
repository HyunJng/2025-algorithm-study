import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    public static int n, m;
    public static final int START = 1;
    public static List<Edge> list = new ArrayList<>();
    public static int[] weight, before;
    public static List<Integer> cycleNumber = new ArrayList<>();
    public static Map<Integer, List<Edge>> graph = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        weight = new int[n + 1];
        before = new int[n + 1];
        Arrays.fill(weight, Integer.MIN_VALUE);

        for (int i = 0; i <= n; i++) graph.put(i, new ArrayList<>());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(u, v, w);
            list.add(edge);
            graph.get(u).add(edge);
        }

        weight[START] = 0;
        bellman_ford();
        if (reachable() && !inCycle()) {
            List<Integer> result = new ArrayList<>();
            result.add(n);

            int b = before[n];
            while (b != 0) {
                result.add(b);
                b = before[b];
            }
            Collections.reverse(result);
            System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        } else {
            System.out.println("-1");
        }
    }

    public static boolean reachable() {
        return weight[n] != Integer.MIN_VALUE;
    }

    public static boolean inCycle() {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>(cycleNumber);

        while (!queue.isEmpty()) {
            Integer t = queue.poll();
            for (Edge e : graph.get(t)) {
                if(visited[e.end]) continue;
                if(e.end == n) return true;
                visited[t] = true;
                queue.add(e.end);
            }
        }
        return false;
    }

    public static void bellman_ford() {
        for (int i = 1; i < n; i++) {
            boolean changed = false;
            for (Edge target : list) {
                if (weight[target.start] != Integer.MIN_VALUE && weight[target.end] < weight[target.start] + target.weight) {
                    weight[target.end] = weight[target.start] + target.weight;
                    before[target.end] = target.start;
                    changed = true;
                }
            }
            if(!changed) break;
        }

        for (Edge target : list) {
            if (weight[target.start] != Integer.MIN_VALUE  && weight[target.end] < weight[target.start] + target.weight) {
                cycleNumber.add(target.end);
            }
        }
    }


    public static class Edge {
        public int start, end;
        public int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}