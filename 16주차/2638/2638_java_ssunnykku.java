import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] len = br.readLine().split(" ");

        int n = Integer.parseInt(len[0]);
        int m = Integer.parseInt(len[1]);

        String[][] paper = new String[n][m];

        for (int i = 0; i < n; i++) {
            paper[i] = br.readLine().split(" ");
        }

        int time = 0;
        int lastCheese = 0;

        while (true) {
            markOutsideAir(n, m, paper);

            List<int[]> melted = new ArrayList<>();
            int cheeseCount = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (paper[i][j].equals("1")) {
                        cheeseCount++;
                        int airTouch = 0;
                        for (int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                                if (paper[nx][ny].equals("-1")) {
                                    airTouch++;
                                }
                            }
                        }
                        if (airTouch >= 2) {
                            melted.add(new int[] { i, j });
                        }
                    }
                }
            }

            if (cheeseCount == 0) {
                System.out.println(time);
                break;
            }

            for (int[] loc : melted) {
                paper[loc[0]][loc[1]] = "0";
            }

            time++;
            lastCheese = cheeseCount;
        }
    }

    static void markOutsideAir(int n, int m, String[][] paper) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { 0, 0 });
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            paper[x][y] = "-1";

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                    if (paper[nx][ny].equals("0") || paper[nx][ny].equals("-1")) {
                        visited[nx][ny] = true;
                        q.add(new int[] { nx, ny });
                    }
                }
            }
        }
    }
}