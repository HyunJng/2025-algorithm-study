import java.io.*;

public class Main {
    static int N, result = 0;
    static int[] queen;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        queen = new int[N];
        backtrack(0);
        System.out.println(result);
    }

    static void backtrack(int row) {
        if (row == N) {
            result++;
            return;
        }
        
        for (int col = 0; col < N; col++) {
            if (check(row, col)) {
                queen[row] = col;
                backtrack(row + 1);
            }
        }
    }
    
    static boolean check(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queen[i] == col || Math.abs(queen[i] - col) == row - i) {
                return false;
            }
        }
        return true;
    }
}
