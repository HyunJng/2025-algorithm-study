import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static int MAX = 5000;
    public static int MOD = 1_000_000_007;
    public static long[] dp;

    public void initDP() {

        dp = new long[MAX / 2 + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= MAX / 2; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = (dp[i] + dp[i - j] * dp[j - 1]) % MOD;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Main main = new Main();

        main.initDP();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int l = Integer.parseInt(br.readLine());
            long answer = l % 2 == 0 ? dp[l / 2] : 0L;
            bw.write(answer + "\n");
        }

        bw.flush();

        br.close();
        bw.close();
    }
}