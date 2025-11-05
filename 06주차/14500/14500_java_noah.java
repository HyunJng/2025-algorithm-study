import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int max = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, board[i][j]);
                visited[i][j] = false;
                checkExtraShape(i, j);
            }
        }

        System.out.println(max);
    }

    static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, depth + 1, sum + board[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    static void checkExtraShape(int x, int y) {
        int center = board[x][y];
        int wings = 0;
        int minWing = Integer.MAX_VALUE;
        int sum = center;

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            wings++;
            int val = board[nx][ny];
            sum += val;
            minWing = Math.min(minWing, val);
        }

        if (wings == 4) sum -= minWing;

        if (wings >= 3)
            max = Math.max(max, sum);
    }
}