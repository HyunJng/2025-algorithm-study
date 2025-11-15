import java.io.*;
import java.util.*;

public class Main {
    static int SIZE = 101, N;
    static boolean[][] board = new boolean[SIZE][SIZE];
    static Point[] checkedSquare = {new Point(1, 0), new Point(0, 1), new Point(1, 1)};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            List<Point> list = new ArrayList<>();
            addList(list, new Point(x, y));
            addList(list, dragonCurveInit(list.get(0), direction));
            while (time-- > 0) {
                dragonCurve(list);
            }
        }
        System.out.println(countSquare());
    }

    public static void addList(List<Point> list, Point p) {
        if(!isAvailable(p.x, p.y)) return;

        list.add(p);
        board[p.y][p.x] = true;
    }

    public static boolean isAvailable(int x, int y) {
        return 0 <= x && x < SIZE && 0 <= y && y < SIZE;
    }

    public static int countSquare() {
        int result = 0;
        for (int i = 0; i < SIZE - 1; i ++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if(!board[i][j]) continue;

                int cnt = 0;
                for (Point p : checkedSquare) {
                    cnt += board[i + p.y][j + p.x] ? 1 : 0;
                }
                if (cnt == 3) result++;
            }
        }
        return result;
    }

    public static void dragonCurve(List<Point> list) {
        Point start = list.get(list.size() - 1);

        for (int i = list.size() - 1; i >= 0; i--) {
            Point target = list.get(i);

            int temp_x = (start.x - target.x) * -1;
            int temp_y = start.y - target.y;
            if (temp_x == 0 && temp_y == 0) continue;

            addList(list, new Point(start.x + temp_y, start.y + temp_x));
        }
    }

    public static Point dragonCurveInit(Point start, int direction) {
        switch (direction) {
            case 0: return new Point(start.x + 1, start.y);
            case 1: return new Point(start.x, start.y - 1);
            case 2: return new Point(start.x - 1, start.y);
            default: return new Point(start.x, start.y + 1);
        }
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}