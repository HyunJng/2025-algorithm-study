import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static long mod = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
            n = Integer.parseInt(br.readLine());

            for(int i = 0; i < n; i++) {
                bw.append(String.valueOf(solution(Integer.parseInt(br.readLine()))));
                bw.append("\n");
            }
            bw.flush();
        }
    }

    public static long solution(int k) {
        if(k % 2 != 0) return 0;
        int half = k / 2;
        long[] dp = new long[half + 1];

        dp[0] = 1;
        for (int i = 1; i <= half; i++) {
            long sum = 0;
            for (int j = 0; j < i; j++) {
                sum += dp[j] * dp[i - j - 1] % mod;
            }
            dp[i] = sum % mod;
        }

        return dp[half];
    }
}