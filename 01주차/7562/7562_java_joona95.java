import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static boolean[][] visited;
    private static final int MOVE_CNT = 8;
    private static final int[] dx = {1, 2, 1, 2, -1, -2, -1, -2};
    private static final int[] dy = {2, 1, -2, -1, 2, 1, -2, -1};

    class Node {
        private final int x;
        private final int y;
        private final int level;

        public Node(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }

    public int bfs(int n, int rootX, int rootY, int destinationX, int destinationY) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(rootX, rootY, 0));
        visited[rootX][rootY] = true;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.x == destinationX && node.y == destinationY) {
                return node.level;
            }

            for (int i = 0; i < MOVE_CNT; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    queue.add(new Node(nx, ny, node.level + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Main main = new Main();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            visited = new boolean[n][n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int rootX = Integer.parseInt(st.nextToken());
            int rootY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int destinationX = Integer.parseInt(st.nextToken());
            int destinationY = Integer.parseInt(st.nextToken());

            int answer = main.bfs(n, rootX, rootY, destinationX, destinationY);

            bw.write(answer + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}