import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        input();

        simulation();

        System.out.println(answer);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean[][] visited;
    private static void simulation() {
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, 0);
                visited[i][j] = false;

                //중간에 대한 검증
                checkT(i, j);
            }
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean isOutOfBound(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
    private static void dfs(int x, int y, int depth, int sum) {
        sum += map[x][y];

        if (depth >= 4) {
            answer = Math.max(sum, answer);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nX = x + dx[d];
            int nY = y + dy[d];
            if (!isOutOfBound(nX, nY) && !visited[nX][nY]) {
                visited[nX][nY] = true;
                dfs(nX, nY, depth + 1, sum);
                visited[nX][nY] = false;
            }
        }
    }

    private static void checkT(int i, int j) {
        if (j + 2 < M && i + 1 < N) {
            answer = Math.max(answer, map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 1]);
        }

        if (j + 2 < M && i - 1 >= 0) {
            answer = Math.max(answer, map[i][j] + map[i][j + 1] + map[i - 1][j + 1] + map[i][j + 2]);
        }

        if (i + 2 < N && j + 1 < M) {
            answer = Math.max(answer, map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 1][j + 1]);
        }

        if (i - 1 >= 0 && i + 1 < N && j + 1 < M) {
            answer = Math.max(answer, map[i][j] + map[i][j + 1] + map[i - 1][j + 1] + map[i + 1][j + 1]);
        }
    }
}