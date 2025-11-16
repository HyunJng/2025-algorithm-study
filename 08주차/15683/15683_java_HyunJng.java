import java.io.*;
import java.util.*;

public class Main {
    static int N, M, maxCheckingArea = Integer.MIN_VALUE;
    static int[][] board, visited;
    static int BLOCK = 6;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Camera[] candidate = {
            new Camera(null),
            new Camera(new int[][]{{0}, {1}, {2}, {3}}),
            new Camera(new int[][]{{0, 2}, {1, 3}}),
            new Camera(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}}),
            new Camera(new int[][]{{0, 1, 2}, {1, 0, 3}, {0, 2, 3}, {1, 2, 3}}),
            new Camera(new int[][]{{0, 1, 2, 3}})
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        backtrack(0, 0, checkCctv());
        System.out.println(countZeroArea() - maxCheckingArea);
    }

    public static int countZeroArea() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) result++;
            }
        }
        return result;
    }

    public static void backtrack(int level, int checkingArea, List<Integer[]> cctv) {
        if (level == cctv.size()) {
            maxCheckingArea = Math.max(maxCheckingArea, checkingArea);
            return;
        }

        Integer[] targetSpot = cctv.get(level);
        Camera target = candidate[board[targetSpot[1]][targetSpot[0]]];
        for (int[] dir : target.dirs) {
            int area = plusCheckingArea(dir, targetSpot);
            backtrack(level + 1, checkingArea + area, cctv);
            minusCheckingArea(dir, targetSpot);
        }

    }

    public static boolean isAvailable(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N && board[y][x] != BLOCK;
    }

    public static int plusCheckingArea(int[] dir, Integer[] spot) {
        int newCheckingAreaCount = 0;

        for (int d : dir) {
            int x = spot[0];
            int y = spot[1];
            while (isAvailable(x + dx[d], y + dy[d])) {
                x += dx[d];
                y += dy[d];
                if (visited[y][x] == 0 && board[y][x] == 0) newCheckingAreaCount++;

                visited[y][x]++;
            }
        }
        return newCheckingAreaCount;
    }

    public static void minusCheckingArea(int[] dir, Integer[] spot) {
        for (int d : dir) {
            int x = spot[0];
            int y = spot[1];
            while (isAvailable(x + dx[d], y + dy[d])) {
                x += dx[d];
                y += dy[d];
                visited[y][x]--;
            }
        }
    }

    public static List<Integer[]> checkCctv() {
        List<Integer[]> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != 0 && board[i][j] != BLOCK) result.add(new Integer[]{j, i});
            }
        }
        return result;
    }

    public static class Camera {
        int[][] dirs;

        public Camera(int[][] dirs) {
            this.dirs = dirs;
        }
    }
}