import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer, safeArea, WALL_COUNT = 3;
    // 0-index
    static int map[][];
    static boolean visited[][];
    // y * M + x
    static Set<Integer> visitedWall = new HashSet<>();
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int dfs(int x, int y) {
        visited[y][x] = true;
        int infected = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= M || ny < 0 || ny >= N ||
                    visited[ny][nx] ||
                    map[ny][nx] != 0 ||
                    visitedWall.contains(ny * M + nx)) {
                continue;
            }
            infected += dfs(nx, ny);
        }
        return infected;
    }

    static void checkWall() {
        if (visitedWall.size() == WALL_COUNT) {
            // 해당 벽을 사용해서 탐색 시작하기
            visited = new boolean[N][M];
            int infected = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 2) {
                        int newInfected = dfs(j, i) - 1;
                        infected += newInfected;
                    }
                }
            }
            answer = Math.max(answer, safeArea - infected);
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 빈 공간이거나, 방문하지 않은 곳이라면 탐색
                if (!visitedWall.contains(i * M + j) && map[i][j] == 0) {
                    visitedWall.add(i * M + j);
                    checkWall();
                    visitedWall.remove(i * M + j);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        answer = 0;
        safeArea = 0;
        int[] raw1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = raw1[0];
        M = raw1[1];
        map = new int[N][M];
        visitedWall = new HashSet<>();

        for (int i = 0; i < N; i++) {
            int[] raw2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = raw2[j];
                if (raw2[j] == 0) {
                    safeArea++;
                }
            }
        }
        // 무조건 3개의 벽이 설치되어야 하므로
        safeArea -= WALL_COUNT;

        // 벽이 세워질 공간을 백트래킹으로 탐색하기
        checkWall();

        // 답 출력
        System.out.println(answer);
    }
}