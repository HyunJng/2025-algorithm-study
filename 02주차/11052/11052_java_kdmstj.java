import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] pArr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        //1. 입력
        input();

        //2. 구현
        simulation();

        //3.결과
        result();
    }

    private static void result() {
        System.out.println(dp[N]);
    }

    private static void simulation() {
        for (int i = 1; i <= N; i++) {
            int best = 0;
            for (int j = 1; j <= i; j++) {
                best = Math.max(best, dp[i - j] + pArr[j]);
            }
            dp[i] = best;
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pArr = new int[N + 1];
        dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            pArr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
