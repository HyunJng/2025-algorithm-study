import java.io.*;
import java.util.*;

public class Main {
    public static int days;
    public static int[] time, pay, dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        days = Integer.parseInt(br.readLine());
        time = new int[days + 1];
        pay = new int[days + 1];
        dp = new int[days + 1];
        
        for (int i = 0; i < days; i++) {
            String[] input = br.readLine().split(" ");
            time[i] = Integer.parseInt(input[0]);
            pay[i] = Integer.parseInt(input[1]);
        }
        
        System.out.println(getMaxProfit() + "");
        
    }
    
    public static int getMaxProfit() {
        for (int day = days - 1; day >= 0; day--) {
            dp[day] = dp[day + 1];
            
            if (day + time[day] <= days) {
                dp[day] = Math.max(dp[day], dp[day + time[day]] + pay[day]);
            }
        }
        
        return dp[0];
    }
}