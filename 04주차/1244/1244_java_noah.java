import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[] sw = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sw[i] = Integer.parseInt(st.nextToken());
        }
        
        int M = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            
            if (g == 1) {
                for (int j = n; j <= N; j += n) {
                    sw[j] = 1 - sw[j];
                }
            } else {
                int l = n, r = n;
                sw[n] = 1 - sw[n];
                
                while (l > 1 && r < N && sw[l - 1] == sw[r + 1]) {
                    sw[--l] = 1 - sw[l];
                    sw[++r] = 1 - sw[r];
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            System.out.print(sw[i]);
            if (i % 20 == 0) System.out.println();
            else if (i < N) System.out.print(" ");
        }
        if (N % 20 != 0) System.out.println();
    }
}

