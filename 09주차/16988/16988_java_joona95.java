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
    public static final int MINE = 1;
    public static final int OTHER = 2;
    public static final int BADUK_COUNT = 2;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    public static int answer = Integer.MIN_VALUE;
    public static int n, m;
    public static int[][] arr;
    public static boolean[][] checked;

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

        markMine(0);

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    private static void markMine(int level) {

        if (level == BADUK_COUNT) {
            answer = Math.max(answer, countCapturableStones());
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == EMPTY) {
                    arr[i][j] = MINE;
                    markMine(level + 1);
                    arr[i][j] = EMPTY;
                }
            }
        }
    }

    private static int countCapturableStones() {
        int answer = 0;
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == OTHER && !checked[i][j]) {
                    answer += countAdjacentCapturableStones(i, j);
                }
            }
        }
        return answer;
    }

    private static int countAdjacentCapturableStones(int x, int y) {
        int count = 1;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        checked[x][y] = true;
        boolean isNotCapturable = false;
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int k = 0; k < dx.length; k++) {
                int nx = current.x + dx[k];
                int ny = current.y + dy[k];
                if (isInBounds(nx, ny) && !checked[nx][ny]) {
                    if (arr[nx][ny] == MINE) {
                        continue;
                    }
                    if (arr[nx][ny] == EMPTY) {
                        isNotCapturable = true;
                    }
                    if (arr[nx][ny] == OTHER) {
                        queue.add(new Point(nx, ny));
                        checked[nx][ny] = true;
                        count++;
                    }
                }
            }
        }
        return isNotCapturable ? 0 : count;
    }

    private static boolean isInBounds(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < n && ny < m;
    }
}