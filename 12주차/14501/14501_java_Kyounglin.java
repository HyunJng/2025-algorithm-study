import java.io.*;
import java.util.*;

public class 14501_java_Kyounglin {
        public static void main(String[]args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] t = new int[n+1];
        int[] p = new int[n+1];
        int[] dp = new int[n+2];

        StringTokenizer st;
        for(int i = 1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());

        }

        for(int i = 1; i<=n; i++){
            dp[i] = Math.max(dp[i], dp[i-1]);
            if(i+t[i]<=n+1){
                dp[i+t[i]] = Math.max(dp[i+t[i]], dp[i]+p[i]);
            }
        }
        dp[n+1] = Math.max(dp[n], dp[n+1]);
        System.out.println(dp[n+1]);
    }
    
}
