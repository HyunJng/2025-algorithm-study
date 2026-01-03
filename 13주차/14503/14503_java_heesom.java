import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static int r, c, d;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        map = new int[n][m];

        line = br.readLine().split(" ");
        r = Integer.parseInt(line[0]);
        c = Integer.parseInt(line[1]);
        d = Integer.parseInt(line[2]);

        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        System.out.println(clean());
    }

    static int clean() {
        int cleaned = 0;
        int turn = 0;

        while (true) {
            if (map[r][c] == 0) {
                map[r][c] = 2;
                cleaned++;
            }

            if (turn == 4) {
                int bx = r - dx[d];
                int by = c - dy[d];

                if (map[bx][by] == 1) {
                    return cleaned;
                }

                r = bx;
                c = by;
                turn = 0;
                continue;
            }

            d = (d + 3) % 4;
            int nx = r + dx[d];
            int ny = c + dy[d];

            if (map[nx][ny] == 0) {
                r = nx;
                c = ny;
                turn = 0;
            } else {
                turn++;
            }
        }
    }
}