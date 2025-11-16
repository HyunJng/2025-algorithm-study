import java.io.*;
import java.util.*;

public class Main {
    static Point[] A_MOVE = {new Point(1, 0), new Point(0, -1), new Point(-1, 0), new Point(0, 1)};
    static Point[] B_MOVE = {new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1)};
    static Point[] POLLUTE_MOVE = {new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1)};
    static final int CLEANER = -1;
    static int[][] before, after;
    static Point A, B;
    static int R, C, T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        before = new int[R][C];
        after = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                before[i][j] = Integer.parseInt(st.nextToken());
                if(before[i][j] == CLEANER) {
                    if(A == null) A = new Point(j, i);
                    else B = new Point(j, i);
                };
            }
        }

        for (int i = 0; i < T; i++) {
            pollute();
            airClean(A_MOVE, A);
            airClean(B_MOVE, B);
        }

        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(before[i][j] == CLEANER) continue;
                answer += before[i][j];
            }
        }
        System.out.println(answer);
    }

    public static void airClean(Point[] moveArr, Point start) {
        int a_x = start.x, a_y = start.y, a_after_x, a_after_y;

        after =  Arrays.stream(before)
                .map(arr -> Arrays.copyOf(arr, arr.length))
                .toArray(int[][]::new);;
        for (Point move : moveArr) {
            a_after_x = a_x;
            a_after_y = a_y;

            do {
                a_x = a_after_x;
                a_y = a_after_y;

                a_after_x += move.x;
                a_after_y += move.y;

                if(isAvailable(a_after_x, a_after_y)){
                    if(before[a_y][a_x] == CLEANER)
                        after[a_after_y][a_after_x] = 0;
                    else if(before[a_after_y][a_after_x] == CLEANER);
                    else after[a_after_y][a_after_x] = before[a_y][a_x];
                }
            } while (isAvailable(a_after_x, a_after_y));
        }

        before = after;
        after = new int[R][C];
    }

    public static void pollute() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(before[i][j] == CLEANER ) { after[i][j] = before[i][j]; continue;}
                if(before[i][j] == 0) continue;

                int cnt = 0;
                for(Point move : POLLUTE_MOVE){
                    if(!isAvailable(j + move.x, i + move.y)) continue;
                    after[i + move.y][j + move.x] += before[i][j] / 5;
                    cnt++;
                }
                after[i][j] += before[i][j] - (before[i][j] / 5 * cnt);
            }
        }
        before = after;
        after = new int[R][C];
    }

    public static boolean isAvailable(int x, int y) {
        return (0 <= x && x < C && 0 <= y && y < R) && before[y][x] != CLEANER;
    }

    public static class Point {
        int y, x;

        public Point(int x, int y) {
            this.y = y;
            this.x = x;
        }
    }
}