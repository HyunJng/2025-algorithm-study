import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] arr = new int[W];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int answer = 0;
        
        for (int i = 1; i < W - 1; i++) {
            int left = 0, right = 0;
            
            for (int j = 0; j < i; j++) {
                left = Math.max(left, arr[j]);
            }
            
            for (int j = i + 1; j < W; j++) {
                right = Math.max(right, arr[j]);
            }
            
            int level = Math.min(left, right);
            if (level > arr[i]) {
                answer += level - arr[i];
            }
        }
        
        System.out.println(answer);
    }
}