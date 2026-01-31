import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");
        int n = Integer.parseInt(info[0]);
        int m = Integer.parseInt(info[1]);
        int r = Integer.parseInt(info[2]);

        String[] nums = br.readLine().split(" ");
        int[] items = new int[n];

        int[][] arr = new int[r][];

        for (int i = 0; i < items.length; i++) {
            items[i] = Integer.parseInt(nums[i]);

        }

        for (int i = 0; i < r; i++) {
            String[] a = br.readLine().split(" ");
            int[] aPerseInt = new int[3];
            for (int j = 0; j < 3; j++) {
                aPerseInt[j] = Integer.parseInt(a[j]);
            }
            arr[i] = aPerseInt;
        }

        int result = 0;
        for (int start = 1; start <= n; start++) {
            result = Math.max(result, solution(arr, start, n, items, m));
        }
        System.out.println(result);
    }

    private static class Node {
        int dest, cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static int solution(int[][] graph, int start, int n, int[] items, int m) {
        ArrayList<Node>[] adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge : graph) {
            adjList[edge[0]].add(new Node(edge[1], edge[2]));
            adjList[edge[1]].add(new Node(edge[0], edge[2])); // 양방향
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.dest] < now.cost)
                continue;

            for (Node nextNode : adjList[now.dest]) {
                if (dist[nextNode.dest] > now.cost + nextNode.cost) {
                    dist[nextNode.dest] = now.cost + nextNode.cost;
                    pq.add(new Node(nextNode.dest, dist[nextNode.dest]));
                }
            }
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= m)
                sum += items[i - 1];
        }
        return sum;
    }

}
