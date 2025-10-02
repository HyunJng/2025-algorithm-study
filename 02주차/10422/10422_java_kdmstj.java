import java.io.*;
import java.util.*;

public class ans10422 {

    static int T;
    static long[] dp;
    static final int MAX = 5000;
    static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        dp = new long[MAX + 1];
        dp[0] = 1;

        pre();
        StringBuilder sb = new StringBuilder();
        for (int test_case = 0; test_case < T; test_case++) {
            int L = Integer.parseInt(br.readLine());

            sb.append((L % 2 == 0) ? dp[L] : 0).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void pre() {
        for (int i = 2; i <= MAX; i += 2) {
            long sum = 0;
            for (int j = 0; j <= i - 2; j++) {
                sum = (sum + (dp[j] * dp[i - 2 - j]) % MOD) % MOD;
            }
            dp[i] = sum;
        }
    }
}
