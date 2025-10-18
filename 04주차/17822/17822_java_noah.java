import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T;
    static int[][] board;
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N + 1][M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) board[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            spin(x, d, k);
            if (!remove()) num();
        }

        System.out.println(sum());
    }

    static void spin(int x, int d, int k) {
        for (int i = x; i <= N; i += x) {
            int[] temp = new int[M];
            for (int j = 0; j < M; j++) {
                int nj = (d == 0) ? (j + k) % M : (j - k + M) % M;
                temp[nj] = board[i][j];
            }
            board[i] = temp;
        }
    }

    static boolean remove() {
        boolean hasSame = false;
        boolean[][] rem = new boolean[N + 1][M];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) continue;
                int cur = board[i][j];
                for (int[] d : dirs) {
                    int ni = i + d[0];
                    int nj = (j + d[1] + M) % M;
                    if (ni < 1 || ni > N) continue;
                    if (board[ni][nj] == cur) {
                        rem[i][j] = rem[ni][nj] = true;
                        hasSame = true;
                    }
                }
            }
        }

        if (hasSame) {
            for (int i = 1; i <= N; i++)
                for (int j = 0; j < M; j++)
                    if (rem[i][j]) board[i][j] = 0;
        }

        return hasSame;
    }

    static void num() {
        int sum = 0, cnt = 0;
        for (int i = 1; i <= N; i++)
            for (int j = 0; j < M; j++)
                if (board[i][j] > 0) { sum += board[i][j]; cnt++; }

        if (cnt == 0) return;
        double avg = (double) sum / cnt;

        for (int i = 1; i <= N; i++)
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) continue;
                if (board[i][j] > avg) board[i][j]--;
                else if (board[i][j] < avg) board[i][j]++;
            }
    }

    static int sum() {
        int total = 0;
        for (int i = 1; i <= N; i++)
            for (int j = 0; j < M; j++) total += board[i][j];
        return total;
    }
}
