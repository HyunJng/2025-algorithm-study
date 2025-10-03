import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public long solution(int l) {

        if (l % 2 != 0) {
            return 0;
        }

        long[] dp = new long[l + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= l / 2; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = (dp[i] + dp[i - j] * dp[j - 1]) % 1000000007;
            }
        }

        return dp[l / 2];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Main main = new Main();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int l = Integer.parseInt(br.readLine());
            long answer = main.solution(l);
            bw.write(answer + "\n");
        }

        bw.flush();

        br.close();
        bw.close();
    }
}