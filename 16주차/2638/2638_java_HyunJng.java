import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static final int CHEESE = 1, EMPTY = 0;
    static int[][] map;
    static boolean[][] outside;
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
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

        System.out.println(solution());
    }

    public static int solution() {
        int time = 0;

        while (true) {
            markOutsideAir();
            List<Point> melt = findMelt();

            if (melt.isEmpty()) break;

            for (Point p : melt) {
                map[p.y][p.x] = EMPTY;
            }
            time++;
        }

        return time;
    }

    public static void markOutsideAir() {
        outside = new boolean[N][M];
        ArrayDeque<Point> q = new ArrayDeque<>();

        outside[0][0] = true;
        q.add(new Point(0, 0));

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if (!inRange(nx, ny)) continue;
                if (outside[ny][nx]) continue;
                if (map[ny][nx] == CHEESE) continue;

                outside[ny][nx] = true;
                q.add(new Point(nx, ny));
            }
        }
    }

    public static List<Point> findMelt() {
        List<Point> melt = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] != CHEESE) continue;

                int touch = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if (!inRange(nx, ny)) continue;
                    if (outside[ny][nx]) touch++;
                }

                if (touch >= 2) melt.add(new Point(x, y));
            }
        }

        return melt;
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    public static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }
}
