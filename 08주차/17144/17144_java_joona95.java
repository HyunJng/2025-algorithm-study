import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int r, c, t;
    public static int[][] arr;
    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        arr = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(String.valueOf(solution()));

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution() {
        List<Integer> cleaner = findCleaner();
        while (t > 0) {
            spreadFineDust();
            cleanTop(cleaner.get(0));
            cleanBottom(cleaner.get(1));
            t--;
        }
        return sumTotalFineDust();
    }

    private static List<Integer> findCleaner() {
        List<Integer> cleaner = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            if (arr[i][0] == -1) {
                cleaner.add(i);
            }
        }
        return cleaner;
    }

    private static void spreadFineDust() {
        int[][] spread = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i][j] > 0) {
                    int quantity = arr[i][j] / 5;
                    for (int k = 0; k < dx.length; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 0 && nx < r && ny >= 0 && ny < c && arr[nx][ny] >= 0) {
                            spread[nx][ny] = spread[nx][ny] + quantity;
                            spread[i][j] -= quantity;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                arr[i][j] += spread[i][j];
            }
        }
    }

    private static void cleanTop(int row) {
        for (int i = row - 1; i > 0; i--) {
            arr[i][0] = arr[i - 1][0];
        }
        for (int i = 0; i < c - 1; i++) {
            arr[0][i] = arr[0][i + 1];
        }
        for (int i = 0; i < row; i++) {
            arr[i][c - 1] = arr[i + 1][c - 1];
        }
        for (int i = c - 1; i > 1; i--) {
            arr[row][i] = arr[row][i - 1];
        }
        arr[row][1] = 0;
    }

    private static void cleanBottom(int row) {
        for (int i = row + 1; i < r - 1; i++) {
            arr[i][0] = arr[i + 1][0];
        }
        for (int i = 0; i < c - 1; i++) {
            arr[r - 1][i] = arr[r - 1][i + 1];
        }
        for (int i = r - 1; i > row; i--) {
            arr[i][c - 1] = arr[i - 1][c - 1];
        }
        for (int i = c - 1; i > 1; i--) {
            arr[row][i] = arr[row][i - 1];
        }
        arr[row][1] = 0;
    }

    private static int sumTotalFineDust() {
        int sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i][j] == -1) {
                    continue;
                }
                sum += arr[i][j];
            }
        }
        return sum;
    }
}
