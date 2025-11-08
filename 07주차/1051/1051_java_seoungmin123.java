package com.example.domain_driven.domain_driven.algorithm.week07;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int M;
    static int N;
    static int[][] square;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        square = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                square[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(solution());

    }

    private static int solution() {
        int maxSize = Math.min(N, M);

        for(int size = maxSize; size >= 1; size--){
            for (int i = 0; i <= N - size; i++) {
                for (int j = 0; j <= M - size; j++) {
                    int leftTop = square[i][j];
                    int rightTop = square[i][j+size-1];
                    int leftBottom = square[i+size-1][j];
                    int rightBottom = square[i+size-1][j+size-1];

                    if(leftTop == rightTop && leftBottom == rightBottom && leftTop == leftBottom){
                        return size * size;
                    }
                }
            }
        }
        return 1;
    }
}
