import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static boolean[][] map;
    static int[][] move = {{1, -1}, {1, 0}, {1, 1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map  = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String temp = br.readLine();
            for (int j = 0; j < C; j++) {
                char t = temp.charAt(j);
                map[i][j] = (t == '.');
            }
        }

        bw.append(String.valueOf(solution()));
        bw.flush();
        bw.close();
    }

    public static int solution() {
        int result = 0;
        for (int y = 0; y < R; y++) {
            if(dfs(0, y)) result++;
        }
        return result;
    }

    public static boolean dfs(int x, int y) {
        map[y][x] = false;
        if(x == C - 1) return true;

        for (int[] m : move) {
            int ty = y + m[1], tx = x + m[0];
            if (isRange(tx, ty) && map[ty][tx]) {
                if(dfs(tx, ty)) return true;
            }
        }
//        map[y][x] = true;
        return false;
    }

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < C && 0 <= y && y < R;
    }
}