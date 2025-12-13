import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static int[] t, p, dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        t = new int[n + 1];
        p = new int[n + 1];
        dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(solution()));

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution() {

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];
            if (i + t[i] <= n) {
                dp[i] = Math.max(dp[i], dp[i + t[i]] + p[i]);
            }
        }
        return dp[0];
    }
}