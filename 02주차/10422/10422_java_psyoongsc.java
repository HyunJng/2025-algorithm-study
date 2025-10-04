package example.org;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int[] arr = new int[T];
        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long[] dp = new long[2501];
        dp[0] = 1;

        for (int i = 1; i <= 2500; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = (dp[i] + (dp[j] * dp[i - 1 - j]) % 1000000007) % 1000000007;
            }
        }

        for (int i = 0; i < T; i++) {
            if(arr[i] % 2 != 0)
                System.out.println(0);
            else
                System.out.println(dp[arr[i] / 2]);
        }
    }
}