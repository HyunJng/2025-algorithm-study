import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] map;

    static boolean checkRow(int row) {
        boolean[] installed = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int diff = map[row][i] - map[row][i + 1];

            if (diff == 0) continue;

            if (Math.abs(diff) > 1) return false;

            if (diff == -1) {
                for (int j = 0; j < L; j++) {
                    if (i - j < 0) return false;
                    if (map[row][i - j] != map[row][i]) return false;
                    if (installed[i - j]) return false;
                }
                for (int j = 0; j < L; j++) {
                    installed[i - j] = true;
                }
            }
            else if (diff == 1) {
                for (int j = 1; j <= L; j++) {
                    if (i + j >= N) return false;
                    if (map[row][i + 1] != map[row][i + j]) return false;
                    if (installed[i + j]) return false;
                }
                for (int j = 1; j <= L; j++) {
                    installed[i + j] = true;
                }
            }
        }

        return true;
    }

    static boolean checkCol(int col) {
        boolean[] installed = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int diff = map[i][col] - map[i + 1][col];

            if (diff == 0) continue;

            if (Math.abs(diff) > 1) return false;

            if (diff == -1) {
                for (int j = 0; j < L; j++) {
                    if (i - j < 0) return false;
                    if (map[i - j][col] != map[i][col]) return false;
                    if (installed[i - j]) return false;
                }
                for (int j = 0; j < L; j++) {
                    installed[i - j] = true;
                }
            }
            else if (diff == 1) {
                for (int j = 1; j <= L; j++) {
                    if (i + j >= N) return false;
                    if (map[i + 1][col] != map[i + j][col]) return false;
                    if (installed[i + j]) return false;
                }
                for (int j = 1; j <= L; j++) {
                    installed[i + j] = true;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        L = input[1];
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int road = 0;

        for (int i = 0; i < N; i++) {
            if (checkRow(i)) road++;
        }

        for (int i = 0; i < N; i++) {
            if (checkCol(i)) road++;
        }

        System.out.println(road);
    }
}