import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static final int SIZE = 101;
    public static int n;
    public static boolean[][] arr = new boolean[SIZE][SIZE];
    public static final int[] dx = {1, 0, -1, 0};
    public static final int[] dy = {0, -1, 0, 1};

    static class Point {
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

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            checkDragonCurve(x, y, d, g);
        }

        int answer = countSquareInDragonCurve();
        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    private static void checkDragonCurve(int x, int y, int d, int g) {

        List<Point> points = new ArrayList<>();
        points.add(new Point(x, y));
        arr[x][y] = true;

        int nx = x + dx[d];
        int ny = y + dy[d];
        if (isInBounds(nx, ny)) {
            points.add(new Point(nx, ny));
            arr[nx][ny] = true;
        }

        while (g > 0) {
            int size = points.size();
            Point last = points.get(size - 1);
            for (int i = size - 2; i >= 0; i--) {
                Point rotate = rotateDragonCurve(last, points.get(i));
                if (isInBounds(rotate.x, rotate.y)) {
                    points.add(rotate);
                    arr[rotate.x][rotate.y] = true;
                }
            }
            g--;
        }
    }

    private static Point rotateDragonCurve(Point start, Point point) {
        int rotateX = -(point.y - start.y) + start.x;
        int rotateY = (point.x - start.x) + start.y;
        return new Point(rotateX, rotateY);
    }

    private static int countSquareInDragonCurve() {
        int answer = 0;
        for (int i = 0; i < SIZE - 1; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if (isSquareInDragonCurve(i, j)) {
                    answer++;
                }
            }
        }
        return answer;
    }

    private static boolean isInBounds(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    private static boolean isSquareInDragonCurve(int x, int y) {
        return arr[x][y] && arr[x + 1][y + 1] && arr[x + 1][y] && arr[x][y + 1];
    }
}