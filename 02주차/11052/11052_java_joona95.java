import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public int solution(int n, int[] arr) {

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = arr[i];
            for (int j = i; j >= i / 2; j--) {
                dp[i] = Math.max(dp[j] + dp[i - j], dp[i]);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Main main = new Main();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = main.solution(n, arr);

        bw.write(String.valueOf(answer));
        bw.flush();

        br.close();
        bw.close();
    }
}