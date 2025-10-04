import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 카드 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] P = new int[N+1];
        int[] dp = new int[N+1];

        String[] split = br.readLine().split(" ");

        for (int i = 1; i < N+1; i++) {
            P[i] = Integer.parseInt(split[i-1]);
        }

        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < i+1; j++) {
                dp[i] = Math.max(dp[i], P[j] + dp[i - j]);
            }
        }
        System.out.println(dp[N]);
    }
}
