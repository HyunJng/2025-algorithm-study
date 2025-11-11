import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] rectangle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        rectangle = new int[N][M];

        for (int i = 0; i < N; i++) {
            rectangle[i] = br.readLine().chars().map(c -> c - '0').toArray();
        }

        int maxSize = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int topLeftCorner = rectangle[i][j];

                for (int k = 1; (j+k < M) && (i+k < N); k++) {
                    int topRightCorner = rectangle[i][j+k];
                    int underLeftCorner = rectangle[i+k][j];
                    int underRightCorner = rectangle[i+k][j+k];

                    if (topLeftCorner == topRightCorner && topLeftCorner == underRightCorner && topLeftCorner == underLeftCorner) maxSize = Math.max(maxSize, k+1);
                }
            }
        }

        System.out.println((int) Math.pow(maxSize, 2));
    }
}