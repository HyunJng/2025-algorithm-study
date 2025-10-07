import java.io.*;

public class Main {
    static void solution(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 파싱
        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n + 1];
        int[] dp = new int[n + 1];
        int answer = 0;

        String[] rawInput = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            input[i + 1] = Integer.parseInt(rawInput[i]);
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = input[i];
            for (int j = 1; j <= i; j++) {
                if (input[j] < input[i] && dp[j] + input[i] > dp[i]) {
                    dp[i] = dp[j] + input[i];
                }
            }
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws IOException {
        solution(args);
    }
}