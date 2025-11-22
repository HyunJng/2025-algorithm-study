import java.io.*;
import java.util.*;

public class Main {
    static int N, M, minVirusArea = Integer.MAX_VALUE;
    static int[][] map;
    static final int VIRUS = 2, EMPTY = 0, WALL = 1, WALL_NUM = 3;
    static int[][] VIRUS_MOVE = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static List<Point> initVirusArea;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution());
    }

    public static int solution() {
        initVirusArea = findInitVirusArea();
        buildWall(0);
        long wallCount = Arrays.stream(map).mapToLong(row -> Arrays.stream(row).filter(i -> i == WALL).count()).sum();
        return (int) (N * M - (wallCount + WALL_NUM + minVirusArea));
    }

    public static void buildWall(int level) {
        if (level == WALL_NUM) {
            minVirusArea = Math.min(checkingVirusArea(), minVirusArea);
            resetVirusArea();
            return;
        }

        for (int temp_y = 0; temp_y < N; temp_y++) {
            for (int temp_x = 0; temp_x < M; temp_x++) {
                if (!isAvailable(temp_x, temp_y)) continue;
                map[temp_y][temp_x] = WALL;
                buildWall(level + 1);
                map[temp_y][temp_x] = EMPTY;
            }
        }
    }

    public static void resetVirusArea() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == VIRUS) map[i][j] = EMPTY;
            }
        }
        for (Point init : initVirusArea) {
            map[init.y][init.x] = VIRUS;
        }
    }

    public static boolean isAvailable(int x, int y) {
        return 0 <= y && y < N && 0 <= x && x < M && map[y][x] == EMPTY;
    }

    public static int checkingVirusArea() {
        Queue<Point> queue = new LinkedList<>(initVirusArea);

        int count = 0;
        while (!queue.isEmpty()) {
            Point target = queue.poll();
            count++;
            if(minVirusArea < count) break;
            for (int[] move : VIRUS_MOVE) {
                int temp_y = target.y + move[1];
                int temp_x = target.x + move[0];
                if (isAvailable(temp_x, temp_y) && map[temp_y][temp_x] == EMPTY) {
                    map[temp_y][temp_x] = VIRUS;
                    queue.add(new Point(temp_x, temp_y));
                }
            }
        }
        return count;
    }

    public static List<Point> findInitVirusArea() {
        List<Point> result = new ArrayList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == VIRUS)
                    result.add(new Point(j, i));
            }
        }
        return result;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}