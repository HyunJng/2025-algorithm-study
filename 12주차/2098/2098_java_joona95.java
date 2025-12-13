import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static int[][] w;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        w = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        bw.write(String.valueOf(solution()));

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution() {

        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[1][0] = 0;
        for (int path = 0; path < (1 << n); path++) {
            for (int current = 0; current < n; current++) {
                if (dp[path][current] == Integer.MAX_VALUE) {
                    continue;
                }

                for (int next = 0; next < n; next++) {
                    if ((path & (1 << next)) != 0) { // 이미 방문한 경우
                        continue;
                    }
                    if (w[current][next] == 0) { // 갈 수 없는 경우
                        continue;
                    }

                    int nextPath = path | (1 << next);
                    dp[nextPath][next] = Math.min(dp[nextPath][next], dp[path][current] + w[current][next]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int current = 0; current < n; current++) {
            if (w[current][0] == 0) {
                continue;
            }
            if (dp[(1 << n) - 1][current] == Integer.MAX_VALUE) {
                continue;
            }

            answer = Math.min(answer, dp[(1 << n) - 1][current] + w[current][0]);
        }

        return answer;
    }
}