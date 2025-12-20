import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] t = new int[n];
        int[] p = new int[n];

        int[] d = new int[n+1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n-1; i >= 0; i--) {
            int time = i+t[i];

            if(time <= n){
                d[i] = Math.max(p[i] + d[time], d[i+1]);
            } else {
                d[i] = d[i+1];
            }
        }
        System.out.println(d[0]);
    }
}