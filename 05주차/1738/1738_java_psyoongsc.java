import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];

        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int i = 0; i < m; i++) {
            int[] road = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph.computeIfAbsent(road[0], k -> new ArrayList<>()).add(new int[]{road[1], road[2]});
        }

        int[] balance = new int[n + 1];
        Arrays.fill(balance, Integer.MIN_VALUE);
        balance[1] = 0;

        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);
        for (int i = 1; i < n; i++) {
            for (var entry : graph.entrySet()) {
                int u = entry.getKey();
                for(var edge : entry.getValue()) {
                    int v = edge[0], w = edge[1];
                    if (balance[u] != Integer.MIN_VALUE && balance[u] + w > balance[v]) {
                        balance[v] = balance[u] + w;
                        parent[v] = u;
                    }
                }
            }
        }

        if (balance[n] == Integer.MIN_VALUE) {
            System.out.println(-1);
            return;
        }

        boolean[] inPositiveCycle = new boolean[n + 1];
        for (var entry : graph.entrySet()) {
            int u = entry.getKey();
            for(var edge : entry.getValue()) {
                int v = edge[0], w = edge[1];
                if (balance[u] != Integer.MIN_VALUE && balance[u] + w > balance[v]) {
                    inPositiveCycle[v] = true;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (var entry : graph.entrySet()) {
                int u = entry.getKey();
                for(var edge : entry.getValue()) {
                    int v = edge[0], w = edge[1];
                    if (inPositiveCycle[u]) {
                        inPositiveCycle[v] = true;
                    }
                }
            }
        }

        if (inPositiveCycle[n]) {
            System.out.println(-1);
            return;
        }

        List<Integer> path = new ArrayList<>();
        int current = n;
        while (current != -1) {
            path.add(current);
            current = parent[current];
        }
        Collections.reverse(path);

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) System.out.print(" ");
        }
    }
}