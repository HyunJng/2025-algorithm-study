import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static final int EMPTY = 0;
    private static final int CHEESE = 1;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int n, m;
    private static int[][] arr;
    private static boolean[][] checked;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(String.valueOf(solution()));

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution() {

        int answer = 0;

        while (removeCheese() != 0) {
            answer++;
        }

        return answer;
    }

    private static int removeCheese() {

        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = arr[i][j];
            }
        }

        checked = new boolean[n][m];
        checked[0][0] = true;
        checkOuterAir(0, 0);

        int removeCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == EMPTY) {
                    continue;
                }

                int airContactCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                        continue;
                    }
                    if (copy[nx][ny] == EMPTY && checked[nx][ny]) {
                        airContactCnt++;
                    }
                }

                if (airContactCnt >= 2) {
                    arr[i][j] = EMPTY;
                    removeCnt++;
                }
            }
        }
        return removeCnt;
    }

    private static void checkOuterAir(int x, int y) {

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }
            if (!checked[nx][ny] && arr[nx][ny] == EMPTY) {
                checked[nx][ny] = true;
                checkOuterAir(nx, ny);
            }
        }
    }
}