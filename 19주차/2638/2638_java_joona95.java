import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int OUTER_AIR = -1;
    private static final int EMPTY = 0;
    private static final int CHEESE = 1;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int n, m;
    private static int[][] arr;

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

        while (true) {
            if (countMeltCheese() == 0) {
                break;
            }
            answer++;
        }

        return answer;
    }

    private static int countMeltCheese() {

        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = arr[i][j];
            }
        }

        checkOuterAir(copy);

        int meltCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == CHEESE) {
                    int outerAirCnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                            continue;
                        }
                        if (copy[nx][ny] == OUTER_AIR) {
                            outerAirCnt++;
                        }
                    }
                    if (outerAirCnt >= 2) {
                        arr[i][j] = EMPTY;
                        meltCnt++;
                    }
                }
            }
        }

        return meltCnt;
    }

    private static void checkOuterAir(int[][] arr) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        arr[0][0] = OUTER_AIR;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m
                        || arr[nx][ny] == OUTER_AIR
                        || arr[nx][ny] == CHEESE) {
                    continue;
                }
                arr[nx][ny] = OUTER_AIR;
                queue.offer(new int[]{nx, ny});
            }
        }
    }
}