import java.io.*;
import java.util.Scanner;

public class Main {
    static void solution(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);

        // 입력 파싱
        int n = sc.nextInt();
        int[] input = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            input[i] = sc.nextInt();
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + input[j]);
            }
        }
        System.out.println(dp[n]);
        sc.close();
    }

    public static void main(String[] args) throws IOException {
        solution(args);
    }
}