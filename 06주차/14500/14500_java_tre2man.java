// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] map;
    static int[][][] domino;

    // 시계 방향으로 90도 회전
    static void turn(int[][] domino) {
        for (int i = 0; i < domino.length; i++) {
            int temp = domino[i][0];
            domino[i][0] = domino[i][1];
            domino[i][1] = temp * -1;
        }
    }

    // 좌우 반전
    static void symmetry(int[][] domino) {
        for (int i = 0; i < domino.length; i++) {
            domino[i][1] *= -1;
        }
    }

    static void updateAnswerByDomino(int[][] domino) {
        getCardinalMax(domino);
        symmetry(domino);
        getCardinalMax(domino);
    }

    private static void getCardinalMax(int[][] domino) {
        int max = 0;
        for (int c = 0; c < 4; c++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int count = 0;
                    for (int k = 0; k < domino.length; k++) {
                        int nn = i + domino[k][0];
                        int nm = j + domino[k][1];
                        if (nn < 0 || nn >= N || nm < 0 || nm >= M) {
                            count = 0;
                            break;
                        }
                        count += map[nn][nm];
                    }
                    max = Math.max(max, count);
                }
            }
            turn(domino);
        }
        answer = Math.max(answer, max);
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        domino = new int[][][] {
                // 직선
                { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } },
                // 정사각형
                { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } },
                // L 모양
                { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } },
                // 지그재그
                { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } },
                // 볼록할철
                { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } }
        };

        for (int i = 0; i < domino.length; i++) {
            updateAnswerByDomino(domino[i]);
        }

        System.out.println(answer);
    }
}