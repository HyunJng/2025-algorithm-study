import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static final int MAX_EXECUTE_CNT = 5;
    private static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
    private static int n;
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, arr);

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int depth, int[][] arr) {

        if (depth == MAX_EXECUTE_CNT) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    answer = Math.max(answer, arr[i][j]);
                }
            }
            return;
        }

        for (int k = 0; k < 4; k++) {

            int[][] copy = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    copy[i][j] = arr[i][j];
                }
            }

            move(copy, k);
            dfs(depth + 1, copy);
        }
    }

    private static void move(int[][] arr, int dir) {
        if (dir == LEFT) {
            for (int r = 0; r < n; r++) {
                int[] line = new int[n];
                for (int c = 0; c < n; c++) {
                    line[c] = arr[r][c];
                }
                int[] next = merge(line);
                for (int c = 0; c < n; c++) {
                    arr[r][c] = next[c];
                }
            }
        } else if (dir == RIGHT) {
            for (int r = 0; r < n; r++) {
                int[] line = new int[n];
                for (int c = 0; c < n; c++) {
                    line[c] = arr[r][n - 1 - c];
                }
                int[] next = merge(line);
                for (int c = 0; c < n; c++) {
                    arr[r][n - 1 - c] = next[c];
                }
            }
        } else if (dir == UP) {
            for (int c = 0; c < n; c++) {
                int[] line = new int[n];
                for (int r = 0; r < n; r++) {
                    line[r] = arr[r][c];
                }
                int[] next = merge(line);
                for (int r = 0; r < n; r++) {
                    arr[r][c] = next[r];
                }
            }
        } else if (dir == DOWN) {
            for (int c = 0; c < n; c++) {
                int[] line = new int[n];
                for (int r = 0; r < n; r++) {
                    line[r] = arr[n - 1 - r][c];
                }
                int[] next = merge(line);
                for (int r = 0; r < n; r++) {
                    arr[n - 1 - r][c] = next[r];
                }
            }
        }
    }

    private static int[] merge(int[] line) {

        int[] tmp = new int[n];
        int t = 0;
        for (int v : line) {
            if (v != 0) {
                tmp[t++] = v;
            }
        }

        int[] merged = new int[n];
        int m = 0;
        int i = 0;
        while (i < t) {
            if (i + 1 < t && tmp[i] == tmp[i + 1]) {
                merged[m++] = tmp[i] * 2;
                i += 2;
            } else {
                merged[m++] = tmp[i];
                i += 1;
            }
        }

        return merged;
    }
}