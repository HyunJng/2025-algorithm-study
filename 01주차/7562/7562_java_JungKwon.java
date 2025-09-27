import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dx = {1, 1, 2, 2, -1, -1, -2, -2};
    static int[] dy = {2, -2, 1, -1, 2, -2, 1, -1};
    static boolean[][] visited;
    static int field;
    static int x2;
    static int y2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int round = Integer.parseInt(br.readLine());

        while (round-- > 0) {
            field = Integer.parseInt(br.readLine());
            visited = new boolean[field][field];
            String[] first = br.readLine().split(" ");
            int x1 = Integer.parseInt(first[0]);
            int y1 = Integer.parseInt(first[1]);

            String[] second = br.readLine().split(" ");
            x2 = Integer.parseInt(second[0]);
            y2 = Integer.parseInt(second[1]);

            System.out.println(bfs(x1, y1));
        }
    }

    static int bfs(int x1, int y1) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x1, y1, 0});
        visited[x1][y1] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int x = node[0];
            int y = node[1];
            int depth = node[2];

            if (x == x2 && y == y2) {
                return depth;
            }

            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= 0 && nx < field && ny >= 0 && ny < field && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, depth + 1});
                }
            }
        }
        return -1;
    }
}