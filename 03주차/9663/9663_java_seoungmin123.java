import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int count = 0;
    static int[] cols;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cols = new int[N];

        backtrack(0);

        System.out.println(count);

    }

    private static void backtrack(int row) {
        if (row == N) {
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if(isVaild(row, col)){
                cols[row] = col;
                backtrack(row + 1);
            }
        }
    }

    private static boolean isVaild(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (cols[i] == col) { //열 체크
                return false;
            }

            //대각선 체크
            if(Math.abs(row - i) == Math.abs(col - cols[i])){
                return false;
            }
        }

        return true;
    }
}
