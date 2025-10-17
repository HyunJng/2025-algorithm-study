package simulation;

import java.util.*;
import java.io.*;

public class ans17822 {
    static int N, M, T;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            simulation(x, d, k);
        }

        output();
    }

    private static void output() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum += map[i][j];
            }
        }
        System.out.println(sum);
    }

    private static void simulation(int x, int d, int k) {
        rotate(x, d, k);

        update();
    }

    static int[] dir = {1, -1};
    private static void rotate(int x, int d, int k) {
        int multiplyNum = 1;
        while (true) {
            int tmpX = x * multiplyNum - 1;
            if (tmpX >= N) break;

            int[] tmp = new int[M];
            for (int i = 0; i < M; i++) {
                tmp[(i + M + dir[d] * k % M) % M] = map[tmpX][i];
            }

            for (int i = 0; i < M; i++) {
                map[tmpX][i] = tmp[i];
            }
            multiplyNum++;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    private static void update() {
        boolean[][] visited = new boolean[N][M];
        boolean isSame = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    isSame = bfs(visited, isSame, i, j);
                }
            }
        }

        if (!isSame) {
            double avg = getAvg();
            if (avg == 0) return;

            updateBy(avg);
        }
    }

    private static boolean bfs(boolean[][] visited, boolean isSame, int i, int j) {
        int num = map[i][j];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;

        boolean isCloser = false;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nX = node[0] + dx[d];
                int nY = (node[1] + dy[d] + M) % M;

                if (isOutOfBound(nX) || visited[nX][nY]) continue;

                if (map[nX][nY] == num) {
                    queue.add(new int[]{nX, nY});
                    visited[nX][nY] = true;
                    map[nX][nY] = 0;
                    isSame = true;
                    isCloser = true;
                }
            }
        }

        if (isCloser) {
            map[i][j] = 0;
        }
        return isSame;
    }

    private static boolean isOutOfBound(int x) {
        return x < 0 || x >= N;
    }

    private static double getAvg() {
        int sum = 0, count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;

                sum += map[i][j];
                count += 1;
            }
        }

        if (count == 0) return 0;

        return (double) sum / count;
    }

    private static void updateBy(double avg) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                if (map[i][j] > avg) {
                    map[i][j] -= 1;
                } else if (map[i][j] < avg) {
                    map[i][j] += 1;
                }
            }
        }
    }
}
