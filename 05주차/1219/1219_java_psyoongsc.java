import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, START, END, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        START = input[1];
        END = input[2];
        M = input[3];

        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int i = 0; i < M; i++) {
            int[] road = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph.computeIfAbsent(road[0], k -> new ArrayList<>()).add(new int[]{road[1], -road[2]});
        }

        int[] earn = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long[] balance = new long[N];
        Arrays.fill(balance, Long.MIN_VALUE);
        balance[START] = earn[START];

        for (int i = 1; i < N; i++) {
            for (var entry : graph.entrySet()) {
                int u = entry.getKey();
                for (var edge : entry.getValue()) {
                    int v = edge[0], w = edge[1];
                    if(balance[u] != Long.MIN_VALUE && balance[u] + w + earn[v] > balance[v]) {
                        balance[v] = balance[u] + w + earn[v];
                    }
                }
            }
        }

        if(balance[END] == Long.MIN_VALUE) {
            System.out.println("gg");
            return;
        }

        boolean[] inPositiveCycle = new boolean[N];
        for (var entry : graph.entrySet()) {
            int u = entry.getKey();
            for (var edge : entry.getValue()) {
                int v = edge[0], w = edge[1];
                if(balance[u] != Long.MIN_VALUE && balance[u] + w + earn[v] > balance[v]) {
                    inPositiveCycle[v] = true;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (inPositiveCycle[i]) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            if(graph.containsKey(u)) {
                for(var edge : graph.get(u)) {
                    int v = edge[0];
                    if (!inPositiveCycle[v]) {
                        inPositiveCycle[v] = true;
                        queue.offer(v);
                    }
                }
            }
        }

        if(inPositiveCycle[END]) {
            System.out.println("Gee");
            return;
        }

        System.out.println(balance[END]);
    }
}