import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            int L = Integer.parseInt(br.readLine());
            if (L % 2 == 1) { 
                System.out.println(0); 
                continue; 
            }
            
            long[] dp = new long[L/2 + 1];
            dp[0] = 1;
            
            for (int i = 1; i <= L/2; i++) {
                for (int j = 0; j < i; j++) {
                    dp[i] = (dp[i] + dp[j] * dp[i-1-j]) % 1000000007;
                }
            }
            
            System.out.println(dp[L / 2]);
        }
    }   
}
