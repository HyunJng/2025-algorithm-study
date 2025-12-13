import java.io.*;
import java.util.*;

public class Main {
    static class Schedule{
        int day, paid;

        Schedule(int day, int paid) {
            this.day = day;
            this.paid = paid;
        }
    }

    static int N;
    static Schedule[] schedule;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        schedule = new Schedule[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int paid = Integer.parseInt(st.nextToken());

            schedule[i] = new Schedule(day, paid);
        }

        System.out.println(solution());
    }

    public static int solution() {
        int[] dp = new int[N + 1];
        int max = Integer.MIN_VALUE;

        for (int i = N - 1; i >= 0; i--) {
            Schedule target = schedule[i];
            if(i + target.day > N) {
                dp[i] = dp[i + 1];
            } else {
                max = Math.max(dp[i + target.day] + target.paid, max);
                dp[i] = max;
            }
        }

        return dp[0];
    }
}