import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M, T;
    static int[][] circle;
    static int[] pos;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input1[0];
        M = input1[1];
        T = input1[2];
        pos = new int[N];
        circle = new int[N][M];

        for (int i = 0; i < N; i++) {
            circle[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < T; i++) {
            int[] input3 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = input3[0];
            int d = input3[1];
            int k = input3[2];

            int shift = k % M;
            for (int j = x - 1; j < N; j += x) {
                int currentPos = pos[j];
                if (d == 0) { // 시계 방향
                    currentPos -= shift;
                    if (currentPos < 0) currentPos += M;
                } else { // 반시계 방향
                    currentPos += shift;
                    if (currentPos >= M) currentPos -= M;
                }
                pos[j] = currentPos;
            }

            boolean[][] erased = new boolean[N][M];
            boolean hasErased = false;

            for (int m = 0; m < N; m++) {
                for (int n = 0; n < M; n++) {
                    int cur = circle[m][(pos[m] + n) % M];
                    if (cur == 0) continue;

                    // 가로 인접 비교
                    int right = circle[m][(pos[m] + n + 1) % M];
                    if (cur == right) {
                        erased[m][(pos[m] + n) % M] = true;
                        erased[m][(pos[m] + n + 1) % M] = true;
                        hasErased = true;
                    }

                    // 세로 인접 비교
                    if (m + 1 < N) {
                        int down = circle[m + 1][(pos[m + 1] + n) % M];
                        if (cur == down) {
                            erased[m][(pos[m] + n) % M] = true;
                            erased[m + 1][(pos[m + 1] + n) % M] = true;
                            hasErased = true;
                        }
                    }
                }
            }

            if (hasErased) {
                for (int o = 0; o < N; o++) {
                    for (int p = 0; p < M; p++) {
                        if (erased[o][p]) circle[o][p] = 0;
                    }
                }
            } else {
                double sum = 0;
                int count = 0;

                for (int o = 0; o < N; o++) {
                    for (int p = 0; p < M; p++) {
                        if (circle[o][p] > 0) {
                            sum += circle[o][p];
                            count++;
                        }
                    }
                }

                if (count > 0) {
                    double avg = sum / count;
                    for (int o = 0; o < N; o++) {
                        for (int p = 0; p < M; p++) {
                            if (circle[o][p] == 0) continue;
                            if (circle[o][p] > avg) circle[o][p]--;
                            else if (circle[o][p] < avg) circle[o][p]++;
                        }
                    }
                }
            }
        }

        int result = 0;
        for (int[] row : circle) {
            for (int v : row) result += v;
        }
        System.out.println(result);
    }
}