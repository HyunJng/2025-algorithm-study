import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {

    private static class Node {
        int dest, cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static void solution(int[][] graph, int[] path, int n) {
        ArrayList<Node>[] adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge : graph) {
            adjList[edge[0]].add(new Node(edge[1], edge[2]));
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[path[0]] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.add(new Node(path[0], 0));

        int[] prev = new int[n + 1];

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.dest] < now.cost) {
                continue;
            } else

                for (Node nextNode : adjList[now.dest]) {
                    if (dist[nextNode.dest] > now.cost + nextNode.cost) {
                        dist[nextNode.dest] = now.cost + nextNode.cost;
                        pq.add(new Node(nextNode.dest, dist[nextNode.dest]));
                        prev[nextNode.dest] = now.dest;

                    }
                }

            if (now.dest == path[1])
                break;

        }

        System.out.println(dist[path[1]]);
        ArrayList<Integer> route = new ArrayList<>();
        for (int v = path[1]; v != 0; v = prev[v]) {
            route.add(v);
            if (v == path[0])
                break;
        }
        Collections.reverse(route);
        System.out.println(route.size());
        System.out.println(route.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] graph = new int[m][];

        for (int i = 0; i < m; i++) {
            String[] route = br.readLine().split(" ");
            graph[i] = Arrays.stream(route)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[] path = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        solution(graph, path, n);

    }

}
