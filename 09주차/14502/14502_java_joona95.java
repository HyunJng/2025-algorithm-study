import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int VIRUS = 2;
    public static final int NEW_WALL_COUNT = 3;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    public static int answer = Integer.MIN_VALUE;
    public static int n, m;
    public static int[][] arr;

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        markNewWall(0);

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    private static void markNewWall(int level) {
        if (level == NEW_WALL_COUNT) {
            int[][] spreadArr = spreadVirus();
            answer = Math.max(answer, countSafeZone(spreadArr));
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == EMPTY) {
                    arr[i][j] = WALL;
                    markNewWall(level + 1);
                    arr[i][j] = EMPTY;
                }
            }
        }
    }

    private static int[][] spreadVirus() {

        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = arr[i][j];
            }
        }

        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == VIRUS) {
                    queue.add(new Point(i, j));
                }
            }
        }

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            for (int i = 0; i < dx.length; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && copy[nx][ny] == EMPTY) {
                    queue.add(new Point(nx, ny));
                    copy[nx][ny] = VIRUS;
                }
            }
        }

        return copy;
    }

    private static int countSafeZone(int[][] arr) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == EMPTY) {
                    count++;
                }
            }
        }
        return count;
    }
}