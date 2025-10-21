import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] BOARD;
    static int COUNT = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        BOARD = new int[N];
        dfs(0);
        System.out.println(COUNT);
    }

    static void dfs(int row) {
        if (row == N) {
            COUNT++;
            return;
        }
        for(int col = 0; col < N; col++) {
            BOARD[row] = col;
            if (isPossible(row)) {
                dfs(row + 1);
            }
        }
    }

    static boolean isPossible(int row) {
        for(int prev =0; prev < row; prev++) {
            if(BOARD[prev] == BOARD[row]) {
                return false;
            }
            if(Math.abs(row-prev) == Math.abs(BOARD[prev]-BOARD[row])) {
                return false;
            }
        }
        return true;
    }
}
