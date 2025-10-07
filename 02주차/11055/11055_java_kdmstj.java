import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        //1. 입력
        input();

        //2. 구현
        simulation();

        //3. 결과
        result();
    }

    static void result() {
        int max = -1;
        for (int i = 0; i < N; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        System.out.println(max);
    }

    static void simulation() {
        dp[0] = arr[0];
        for (int i = 1; i < N; i++) {
            int sum_max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] > sum_max) {
                    sum_max = dp[j];
                }
            }
            dp[i] = arr[i] + sum_max;
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
