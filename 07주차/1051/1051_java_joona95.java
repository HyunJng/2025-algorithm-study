import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static int[][] arr;

    public static int solution(int x, int y) {

        int answer = 1;

        for (int i = 1; i < Math.min(n, m); i++) {
            if (x + i < n && y + i < m && hasSameCorners(i, x, y)) {
                answer = Math.max(answer, (i + 1) * (i + 1));
            }
        }

        return answer;
    }

    private static boolean hasSameCorners(int i, int x, int y) {
        return arr[x][y] == arr[x + i][y] && arr[x][y] == arr[x][y + i] && arr[x][y] == arr[x + i][y + i];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < input.length; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, solution(i, j));
            }
        }

        bw.write(String.valueOf(max));

        bw.flush();
        br.close();
        bw.close();
    }
}
