import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] sq = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                sq[i][j] = line.charAt(j) - '0';
            }
        }

        int max = 1; 

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int limit = Math.min(N - i, M - j);
                for (int d = max; d < limit; d++) {
                    if (sq[i][j] == sq[i + d][j] && sq[i][j] == sq[i][j + d] && sq[i][j] == sq[i + d][j + d]) {
                        max = d + 1;
                    }
                }
            }
        }

        System.out.println(max * max);
    }
}
