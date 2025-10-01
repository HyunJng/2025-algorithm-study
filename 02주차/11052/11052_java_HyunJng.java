import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        String[] inputs = br.readLine().split(" ");
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(inputs[i - 1]);
        }

        System.out.println(solution());
    }

    public static int solution() {
        int[] dp = Arrays.copyOf(arr, arr.length);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[j] + dp[i - j], dp[i]);
            }
        }
        return dp[N];
    }
}