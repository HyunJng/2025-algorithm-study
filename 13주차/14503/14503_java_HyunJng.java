import java.io.*;
import java.util.*;

public class Main {
    public static class Cleaner {
        int x; int y;
        int dir;

        public Cleaner(int y, int x, int dir) {
            this.x = x; this.y = y;
            this.dir = dir;
        }
    }

    static int N, M;
    static int[][] room;
    static final int WALL = 1, DIRTY = 0, CLEAN = -1;
    static final int[][] DIR = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // 북 동 남 서
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];

        st = new StringTokenizer(br.readLine());
        Cleaner cleaner = new Cleaner(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(cleaner));
    }

    public static int solution(Cleaner cleaner) {
        int result = 0;
        do {
            if (room[cleaner.y][cleaner.x] == DIRTY) {
                room[cleaner.y][cleaner.x] = CLEAN;
                result ++;
            }
        } while (updateCleaner(cleaner) || goBack(cleaner));

        return result;
    }

    public static boolean updateCleaner(Cleaner cleaner) {
        boolean c = false;
        for (int i = 0; i < 4; i++) {
            cleaner.dir = (cleaner.dir + 3) % 4;
            int x = cleaner.x + DIR[cleaner.dir][0], y = cleaner.y + DIR[cleaner.dir][1];
            if (!isNotWall(x, y)) continue;
            if (room[y][x] == DIRTY) {
                cleaner.x = x; cleaner.y =y;
                c = true;
                break;
            }
        }
        return c;
    }

    public static boolean goBack(Cleaner cleaner) {
        int newDir = (cleaner.dir + 2) % 4;
        cleaner.x += DIR[newDir][0]; cleaner.y += DIR[newDir][1];
        return room[cleaner.y][cleaner.x] != WALL;
    }

    public static boolean isNotWall(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N && room[y][x] != WALL;
    }
}