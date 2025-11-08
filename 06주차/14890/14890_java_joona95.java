import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static int n, l;
    private static int[][] arr;

    public static int solution() {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (isRowIncline(i)) {
                answer++;
            }
            if (isColumnIncline(i)) {
                answer++;
            }
        }

        return answer;
    }

    private static boolean isRowIncline(int row) {
        boolean[] checked = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            int diff = arr[row][i] - arr[row][i + 1];
            if (diff > 1 || diff < -1) {
                return false;
            }
            if (diff == -1) {
                for (int j = 0; j < l; j++) {
                    if (i - j < 0 || checked[i - j]) {
                        return false;
                    }
                    if (arr[row][i] != arr[row][i - j]) {
                        return false;
                    }
                    checked[i - j] = true;
                }
            }
            if (diff == 1) {
                for (int j = 1; j <= l; j++) {
                    if (i + j >= n || checked[i + j]) {
                        return false;
                    }
                    if (arr[row][i] - 1 != arr[row][i + j]) {
                        return false;
                    }
                    checked[i + j] = true;
                }
            }
        }
        return true;
    }

    private static boolean isColumnIncline(int col) {
        boolean[] checked = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            int diff = arr[i][col] - arr[i + 1][col];
            if (diff > 1 || diff < -1) {
                return false;
            }
            if (diff == -1) {
                for (int j = 0; j < l; j++) {
                    if (i - j < 0 || checked[i - j]) {
                        return false;
                    }
                    if (arr[i][col] != arr[i - j][col]) {
                        return false;
                    }
                    checked[i - j] = true;
                }
            }
            if (diff == 1) {
                for (int j = 1; j <= l; j++) {
                    if (i + j >= n || checked[i + j]) {
                        return false;
                    }
                    if (arr[i][col] - 1 != arr[i + j][col]) {
                        return false;
                    }
                    checked[i + j] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(String.valueOf(solution()));

        bw.flush();
        br.close();
        bw.close();
    }
}