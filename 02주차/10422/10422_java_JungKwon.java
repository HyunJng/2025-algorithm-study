import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        int max = 5000;
        int mod = 1_000_000_007;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[max+1];
        dp[0] = 1;

        for(int i = 2; i <= max; i+=2) {
            for(int j = 0; j <= i-2; j+=2) {
                dp[i] = (dp[i] + dp[j] * dp[i-2-j]) % mod;
            }
        }

        for(int i = 0; i < N; i++) {
            int L = Integer.parseInt(br.readLine());
            bw.write(Long.toString(dp[L]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
