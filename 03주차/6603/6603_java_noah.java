import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static int[] result = new int[6];
    static int k;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            
            if (k == 0) break;
            
            arr = new int[k];
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            backtrack(0, 0);
            System.out.println();
        }
    }
    
    static void backtrack(int start, int depth) {
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }
        
        for (int i = start; i < k; i++) {
            result[depth] = arr[i];
            backtrack(i + 1, depth + 1);
        }
    }
}
