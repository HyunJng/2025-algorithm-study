import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int ITEM_PER_LINE = 3;

    static int TC;
    static int[] inputArr = new int[ITEM_PER_LINE];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());
        for(int T = 0; T < TC; T++) {
            inputArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Map<Integer, List<int[]>> graph = new HashMap<>();

            for (int i = 0; i < inputArr[1]; i++) {
                int[] road = new int[ITEM_PER_LINE];
                road = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                graph.computeIfAbsent(road[0], k -> new ArrayList<>()).add(new int[]{road[1], road[2]});
                graph.computeIfAbsent(road[1], k -> new ArrayList<>()).add(new int[]{road[0], road[2]});
            }

            for (int i = 0; i < inputArr[2]; i++) {
                int[] road = new int[ITEM_PER_LINE];
                road = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                graph.computeIfAbsent(road[0], k -> new ArrayList<>()).add(new int[]{road[1], -road[2]});
            }

            int[] weight = new int[inputArr[0] + 1];
            Arrays.fill(weight, Integer.MAX_VALUE);

            for (int i = 1; i <= inputArr[0]; i++) {
                graph.computeIfAbsent(0, k -> new ArrayList<>()).add(new int[]{i, 0});
            }
            weight[0] = 0;

            for (int i = 0; i < inputArr[0]; i++) {
                for (var entry : graph.entrySet()) {
                    int u = entry.getKey();
                    for(int[] edge : entry.getValue()) {
                        int v = edge[0], w = edge[1];
                        if (weight[u] != Integer.MAX_VALUE && weight[u] + w < weight[v]) {
                            weight[v] = weight[u] + w;
                        }
                    }
                }
            }

            boolean hasNegativeCycle = false;
            for (var entry : graph.entrySet()) {
                int u = entry.getKey();
                for(int[] edge : entry.getValue()) {
                    int v = edge[0], w = edge[1];
                    if (weight[u] != Integer.MAX_VALUE && weight[u] + w < weight[v]) {
                        hasNegativeCycle = true;
                        break;
                    }
                }
            }

            if (hasNegativeCycle) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}