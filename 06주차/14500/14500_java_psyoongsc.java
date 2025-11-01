import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][][] tetrominos = new int[][][]
            {
                    {
                            { 1, 1, 1, 1 }
                    },
                    {
                            { 1 },
                            { 1 },
                            { 1 },
                            { 1 }
                    },
                    {
                            { 1, 1 },
                            { 1, 1 }
                    },
                    {
                            { 1, 0 },
                            { 1, 1 },
                            { 1, 0 }
                    },
                    {
                            { 0, 1 },
                            { 1, 1 },
                            { 0, 1 }
                    },
                    {
                            { 0, 1, 0 },
                            { 1, 1, 1 }
                    },
                    {
                            { 1, 1, 1 },
                            { 0, 1, 0 }
                    },
                    {
                            { 1, 0 },
                            { 1, 1 },
                            { 0, 1 }
                    },
                    {
                            { 0, 1 },
                            { 1, 1 },
                            { 1, 0 }
                    },
                    {
                            { 1, 1, 0 },
                            { 0, 1, 1 }
                    },
                    {
                            { 0, 1, 1 },
                            { 1, 1, 0 }
                    },
                    {
                            { 1, 0 },
                            { 1, 0 },
                            { 1, 1 }
                    },
                    {
                            { 0, 1 },
                            { 0, 1 },
                            { 1, 1 }
                    },
                    {
                            { 1, 1 },
                            { 0, 1 },
                            { 0, 1 }
                    },
                    {
                            { 1, 1 },
                            { 1, 0 },
                            { 1, 0 }
                    },
                    {
                            { 1, 0, 0 },
                            { 1, 1, 1 }
                    },
                    {
                            { 1, 1, 1 },
                            { 1, 0, 0 }
                    },
                    {
                            { 1, 1, 1 },
                            { 0, 0, 1 }
                    },
                    {
                            { 0, 0, 1 },
                            { 1, 1, 1 }
                    }
            };
    static int N, M;
    static int[][] board;

    public static int[][] convolve2D(int[][] board, int[][] filter) {
        int boardHeight = N;
        int boardWidth = M;
        int filterHeight = filter.length;
        int filterWidth = filter[0].length;

        int outputHeight = boardHeight - filterHeight + 1;
        int outputWidth = boardWidth - filterWidth + 1;

        int[][] output = new int[outputHeight][outputWidth];

        for (int i = 0; i < outputHeight; i++) {
            for (int j = 0; j < outputWidth; j++) {
                int sum = 0;

                for (int fi = 0; fi < filterHeight; fi++) {
                    for (int fj = 0; fj < filterWidth; fj++) {
                        sum += board[i + fi][j + fj] * filter[fi][fj];
                    }
                }

                output[i][j] = sum;
            }
        }

        return output;
    }

    public static int findMax(int[][] output) {
        int max = 0;
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                if (output[i][j] > max) {
                    max = output[i][j];
                }
            }
        }

        return max;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        board = new int[N][M];

        for(int i = 0; i < N; i++){
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int max = 0;
        for (int[][] tetromino : tetrominos) {
            int[][] convolve = convolve2D(board, tetromino);
            int convolveMax = findMax(convolve);
            if (convolveMax > max) {
                max = convolveMax;
            }
        }

        System.out.println(max);
    }
}