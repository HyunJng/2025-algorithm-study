import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static int n;
    static int maxHeight;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        maxHeight = 1;

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int t = Integer.parseInt(line[j]);
                board[i][j] = t;
                maxHeight = Math.max(maxHeight, t);
            }
        }

        System.out.println(solution());
    }

    public static int solution() {
        int result = 1;

        for (int h = 1; h < maxHeight; h++) {
            boolean[][] visited = new boolean[n][n];
            int cnt = 0;
            for (int ty = 0; ty < n; ty++) {
                for(int tx = 0; tx < n; tx++) {
                    if (board[ty][tx] <= h || visited[ty][tx]) {
                        visited[ty][tx] = true;
                        continue;
                    }
                    cnt++;
                    dfs(new Point(tx, ty), visited, h);
                }
            }

            result = Math.max(result, cnt);
        }

        return result;
    }

    public static void dfs(Point target, boolean[][] visited, int h) {
        if(visited[target.y][target.x]) return;
        visited[target.y][target.x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = target.y + dy[i], nx = target.x + dx[i];
            if(0 > ny || ny >= n || 0 > nx || nx >= n || visited[ny][nx] || board[ny][nx] <= h) continue;
            dfs(new Point(nx, ny), visited, h);
        }
    }

}
class Point{
    int x; int y;

    public Point (int x, int y) {
        this.x = x; this.y = y;
    }
}