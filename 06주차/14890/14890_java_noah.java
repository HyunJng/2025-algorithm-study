import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            if (check(map[i])) count++;
        }

        for (int j = 0; j < N; j++) {
            int[] col = new int[N];
            for (int i = 0; i < N; i++) {
                col[i] = map[i][j];
            }
            if (check(col)) count++;
        }

        System.out.println(count);
    }

    static boolean check(int[] road) {
        boolean[] slope = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int diff = road[i + 1] - road[i];

            if (diff == 0) continue;

            else if (diff == 1) {
                for (int j = 0; j < L; j++) {
                    int idx = i - j;
                    if (idx < 0 || slope[idx]) return false;
                    if (road[idx] != road[i]) return false;
                    slope[idx] = true;
                }
            }

            else if (diff == -1) {
                for (int j = 1; j <= L; j++) {
                    int idx = i + j;
                    if (idx >= N || slope[idx]) return false;
                    if (road[idx] != road[i + 1]) return false;
                    slope[idx] = true;
                }
            }

            else return false;
        }

        return true;
    }
}

