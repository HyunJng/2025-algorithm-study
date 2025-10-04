import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] P = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, P));
    }

    private static int solution(int N, int[] P) {
        int[] dp = new int[N+1];

        for (int i = 1; i <= N; i++) { //카드팩 몇개살지
            for (int j = 1; j <= i; j++) { //경우의 수별 최대 금액
                dp[i] = Math.max(dp[i], dp[i-j] + P[j]);
            }
        }
        return dp[N];
    }
}