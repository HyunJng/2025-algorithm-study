import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Main {

    private static class Node {
        int x, y, cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static int solution(int[][] graph, int n) {

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        int startCost = graph[0][0];

        dist[0][0] = startCost;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.add(new Node(0, 0, startCost));

        int[] dx = { 1, -1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.x][now.y] < now.cost)
                continue;

            for (int dir = 0; dir < 4; dir++) {
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;

                int nextCost = now.cost + graph[nx][ny];

                if (dist[nx][ny] > nextCost) {
                    dist[nx][ny] = nextCost;
                    pq.add(new Node(nx, ny, nextCost));
                }
            }
        }

        return dist[n - 1][n - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> result = new ArrayList<Integer>();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0)
                break;
            int[][] graph = new int[n][];

            for (int i = 0; i < n; i++) {
                String[] arr = br.readLine().split(" ");
                graph[i] = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
            }
            result.add(solution(graph, n));

        }
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Problem " + (i + 1) + ": " + result.get(i));
        }
    }
}
