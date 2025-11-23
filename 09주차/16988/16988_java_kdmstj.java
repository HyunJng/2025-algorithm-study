import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;

    static List<int[]> empties = new ArrayList<>();
    static int answer = 0;

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        input();
        solve();
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
                if (map[i][j] == 0) {
                    empties.add(new int[]{i, j});
                }
            }
        }
    }

    private static void solve() {
        int K = empties.size();
        if (K < 2) {
            answer = 0;
            return;
        }

        for (int i = 0; i < K; i++) {
            for (int j = i + 1; j < K; j++) {
                int[] p1 = empties.get(i);
                int[] p2 = empties.get(j);

                map[p1[0]][p1[1]] = 1;
                map[p2[0]][p2[1]] = 1;

                int captured = countCaptured();
                if (captured > answer) {
                    answer = captured;
                }

                map[p1[0]][p1[1]] = 0;
                map[p2[0]][p2[1]] = 0;
            }
        }
    }

    private static int countCaptured() {
        boolean[][] visited = new boolean[N][M];
        int total = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 2 && !visited[r][c]) {
                    int cnt = bfs(r, c, visited);
                    total += cnt;
                }
            }
        }

        return total;
    }

    private static int bfs(int sr, int sc, boolean[][] visited) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        int size = 0;
        boolean hasLiberty = false;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            size++;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if (map[nr][nc] == 0) {
                    hasLiberty = true;
                } else if (map[nr][nc] == 2 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return hasLiberty ? 0 : size;
    }
}
