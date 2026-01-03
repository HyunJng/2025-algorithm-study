import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] board;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        int sx = 0, sy = 0;

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(line[j]);
                if (board[i][j] == 9) {
                    sx = i;
                    sy = j;
                    board[i][j] = 0;
                }
            }
        }

        System.out.println(bfs(sx, sy));
    }

    static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        q.add(new int[]{x, y});
        visited[x][y] = true;

        int time = 0;
        int shark = 2;
        int eat = 0;
        int answer = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            List<int[]> list = new ArrayList<>();
            while (!q.isEmpty()) {
                list.add(q.poll());
            }
            list.sort((a, b) -> {
                if (a[0] != b[0]) return a[0] - b[0];
                return a[1] - b[1];
            });
            q.addAll(list);

            boolean eatFlag = false;

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int cx = cur[0], cy = cur[1];

                if (board[cx][cy] != 0 && board[cx][cy] < shark) {
                    board[cx][cy] = 0;
                    eat++;

                    if (eat == shark) {
                        shark++;
                        eat = 0;
                    }

                    q.clear();
                    visited = new boolean[n][n];
                    visited[cx][cy] = true;
                    answer = time;
                    eatFlag = true;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                        if (board[nx][ny] <= shark) {
                            q.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }

                if (eatFlag) break;
            }

            time++;
        }

        return answer;
    }
}