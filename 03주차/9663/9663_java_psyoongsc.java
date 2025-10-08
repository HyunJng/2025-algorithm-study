package example.org;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;

    static int[][] chess;
    static int queenCount = 0;

    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        chess = new int[N][N];

        backtracking(0);

        System.out.println(result);
    }

    public static void backtracking(int row) {
        for(int col = 0; col < N; col++) {
            if(chess[row][col] == 0) {
                placeQueen(row, col);

                if(queenCount == N) {
                    result++;
                    removeQueen(row, col);

                    continue;
                }

                backtracking(row + 1);
                removeQueen(row, col);
            }
        }
    }

    public static void placeQueen(int row, int col) {
        chess[row][col] = -1;
        queenCount++;

        for(int i = row + 1; i < N; i++) {
            if(chess[i][col] >= 0) {
                chess[i][col]++;
            }

            int leftDiagonal = col - (i - row);
            if(leftDiagonal >= 0) {
                if(chess[i][leftDiagonal] >= 0) {
                    chess[i][leftDiagonal]++;
                }
            }

            int rightDiagonal = col + (i - row);
            if(rightDiagonal < N) {
                if(chess[i][rightDiagonal] >= 0) {
                    chess[i][rightDiagonal]++;
                }
            }
        }
    }

    public static void removeQueen(int row, int col) {
        chess[row][col] = 0;
        queenCount--;

        for(int i = row + 1; i < N; i++) {
            chess[i][col]--;

            int leftDiagonal = col - (i - row);
            if(leftDiagonal >= 0) {
                chess[i][leftDiagonal]--;
            }

            int rightDiagonal = col + (i - row);
            if(rightDiagonal < N) {
                chess[i][rightDiagonal]--;
            }
        }
    }
}