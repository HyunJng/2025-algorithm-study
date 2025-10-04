package example.org;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[arr.length];

        for (int i = 1; i <= arr.length; i++) {
            dp[i - 1] = arr[i - 1];
            for (int j = 1; j < i; j++) {
                dp[i - 1] = Math.max(dp[i - 1], dp[i - j - 1] + arr[j - 1]);
            }
        }

        System.out.println(dp[arr.length - 1]);
    }
}