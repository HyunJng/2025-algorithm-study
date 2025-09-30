import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        int maxHeight = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int maxSafeAreas = 1;

        for (int waterLevel = 1; waterLevel <= maxHeight; waterLevel++) {
            visited = new boolean[n][n];
            int safeAreaCount = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] > waterLevel && !visited[i][j]) {
                        dfs(i, j, waterLevel);
                        safeAreaCount++;
                    }
                }
            }
            maxSafeAreas = Math.max(maxSafeAreas, safeAreaCount);
        }
        System.out.println(maxSafeAreas);
    }

    static void dfs(int x, int y, int waterLevel) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n
                    && !visited[nx][ny] && map[nx][ny] > waterLevel) {
                dfs(nx, ny, waterLevel);
            }
        }
    }
}