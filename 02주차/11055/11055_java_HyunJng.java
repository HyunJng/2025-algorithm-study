import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }

    public static int solution() {
        int result = 0;
        int[] dp = Arrays.copyOf(arr, arr.length);

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < x; y++) {
                if (arr[x] > arr[y]) {
                    dp[x] = Math.max(dp[y] + arr[x], dp[x]);
                }
            }
            result = Math.max(result, dp[x]);
        }
        return result;
    }
}