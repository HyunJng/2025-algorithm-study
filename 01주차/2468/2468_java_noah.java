import java.util.Scanner;

public class Main {
    static int n;
    static int[][] zones;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        zones = new int[n][n];

        int maxHeight = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                zones[i][j] = sc.nextInt();
                if(maxHeight < zones[i][j]) {
                    maxHeight = zones[i][j];
                }

            }
        }

        int maxSafeZones = 1;

        for (int h = 1; h <= maxHeight; h++) {
            visited = new boolean[n][n];
            int count = countSafeZones(h);
            if(maxSafeZones < count) {
                maxSafeZones = count;
            }
        }

        System.out.println(maxSafeZones);
    }

    private static int countSafeZones(int h) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && zones[i][j] > h) {
                    dfs(i, j, h);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int x, int y, int h) {
        visited[x][y] = true;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if (!visited[nx][ny] && zones[nx][ny] > h) {
                    dfs(nx, ny, h);
                }
            }
        }
    }
}
