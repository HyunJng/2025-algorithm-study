import java.io.*;
import java.util.*;

public class Main {

    static int N, start, end, M;
    static int[] cityCost;
    static List<Edge> edges;
    static List<List<Integer>> reverse;
    static long[] cost;
    static final long MIN_INF = Long.MIN_VALUE / 4;

    public static void main(String[] args) throws IOException {
        input();

        //양의 순환이 있다면
        boolean isPositiveCycle = bellmanFord();

        output(isPositiveCycle);
    }

    private static void output(boolean isPositiveCycle) {
        if (isPositiveCycle) {
            System.out.println("Gee");
        } else if (cost[end] == MIN_INF) {
            System.out.println("gg");
        } else {
            System.out.println(cost[end]);
        }
    }

    private static boolean bellmanFord() {
        cost = new long[N];
        Arrays.fill(cost, MIN_INF);
        cost[start] = cityCost[start];

        for (int i = 1; i <= N - 1; i++) {
            boolean updated = false;
            for (Edge edge : edges) {
                if (isUpdatable(edge)) {
                    cost[edge.to] = cost[edge.from] - edge.cost + cityCost[edge.to];
                    updated = true;
                }
            }

            if (!updated) {
                break;
            }
        }

        boolean[] influence = new boolean[N];
        for (Edge edge : edges) {
            if (isUpdatable(edge)) {
                influence[edge.to] = true;
            }
        }

        boolean[] canReach = reverseReachableFrom();

        for (int v = 0; v < N; v++) {
            if (influence[v] && canReach[v]) return true;
        }
        return false;
    }

    private static boolean isUpdatable(Edge edge) {
        return cost[edge.from] > MIN_INF && cost[edge.from] - edge.cost + cityCost[edge.to] > cost[edge.to];
    }

    private static boolean[] reverseReachableFrom() {
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(end);
        visited[end] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            List<Integer> list = reverse.get(cur);

            for (int i = 0; i < list.size(); i++) {
                int num = list.get(i);
                if (!visited[num]) {
                    queue.add(num);
                    visited[num] = true;
                }
            }
        }

        return visited;
    }


    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        reverse = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            reverse.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, cost));
            reverse.get(to).add(from);
        }

        st = new StringTokenizer(br.readLine());
        cityCost = new int[N];
        for (int i = 0; i < N; i++) {
            cityCost[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static class Edge {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
