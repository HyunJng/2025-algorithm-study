import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    private static int n;
    private static int[] board;
    private static int answer;

    public void nQueen(int depth) {

        if (depth == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            board[depth] = i;

            if (isPossible(depth)) {
                nQueen(depth + 1);
            }
        }
    }

    private boolean isPossible(int col) {
        for (int i = 0; i < col; i++) {
            if (board[col] == board[i]
                    || Math.abs(col - i) == Math.abs(board[col] - board[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main main = new Main();


        n = Integer.parseInt(br.readLine());
        board = new int[n];

        main.nQueen(0);

        bw.write(String.valueOf(answer));
        bw.flush();
        br.close();
        bw.close();
    }
}