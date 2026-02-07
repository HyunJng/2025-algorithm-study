import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int n;
    private static int[][] map;
    private static int[][] dist;

    public static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ;

        StringBuilder sb = new StringBuilder();
        int idx = 1;
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("Problem ").append(idx).append(": ").append(solution()).append("\n");
            idx++;
        }

        bw.write(sb.toString());

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        pq.add(new Node(0, 0, 0));
        dist[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (isInBound(nx, ny) && dist[nx][ny] > dist[node.x][node.y] + map[nx][ny]) {
                    dist[nx][ny] = dist[node.x][node.y] + map[nx][ny];
                    pq.add(new Node(nx, ny, dist[node.x][node.y] + map[nx][ny]));
                }
            }
        }

        return dist[n - 1][n - 1];
    }

    private static boolean isInBound(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}