import java.util.*;
import java.io.*;

public class ans1738 {

    static int N, M;
    static List<Edge> edges;
    static List<List<Integer>> radj;
    static int[] cost;
    static int[] prev;
    static final int MIN_INF = -1_000_000_000;

    public static void main(String[] args) throws IOException {
        input();

        boolean isCycle = bellmanFord(1);

        output(isCycle);
    }

    private static void output(boolean isCycle) {
        if (isCycle || cost[N] == MIN_INF) {
            System.out.println(-1);
            return;
        }

        Stack<Integer> path = new Stack<>();
        path.add(N);
        while (true) {
            int currentNum = path.peek();
            int prevNum = prev[currentNum];
            path.add(prevNum);

            if (prevNum == 1) break;
        }

        StringBuilder sb = new StringBuilder();
        while (!path.isEmpty()) {
            sb.append(path.pop()).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static boolean bellmanFord(int start) {
        prev = new int[N + 1];
        Arrays.fill(prev, -1);
        cost = new int[N + 1];
        Arrays.fill(cost, MIN_INF);

        cost[start] = 0;

        for (int i = 1; i <= N - 1; i++) {
            boolean isUpdate = false;
            for (Edge edge : edges) {
                if (isUpdatable(edge)) {
                    cost[edge.to] = cost[edge.from] + edge.cost;
                    prev[edge.to] = edge.from;
                    isUpdate = true;
                }
            }
            if (!isUpdate) break;
        }

        // 여전히 커질 수 있는 정점들
        boolean[] influence = new boolean[N + 1];
        for (Edge edge : edges) {
            if (isUpdatable(edge)) {
                influence[edge.to] = true;
            }
        }

        boolean[] canReachN = reverseReachableFrom(N);

        for (int v = 1; v <= N; v++) {
            if (influence[v] && canReachN[v]) return true;
        }
        return false;
    }

    private static boolean isUpdatable(Edge edge) {
        return cost[edge.from] != MIN_INF && cost[edge.from] + edge.cost > cost[edge.to];
    }

    private static boolean[] reverseReachableFrom(int start) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int next : radj.get(cur)){
                if(!visited[next]){
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        return visited;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        radj = new ArrayList<>();
        for(int i= 0; i <= N; i++){
            radj.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, w));
            radj.get(v).add(u);
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
