// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int N, L, answer;
    static int[][] map;

    static boolean calRow(int row) {
        boolean[] installed = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int diff = map[row][i + 1] - map[row][i];
            // 인접한 높이가 2 이상일 경우 경사로 설치 불가능
            if (Math.abs(diff) >= 2)
                return false;
            // 오르막일 경우
            if (diff == 1) {
                for (int j = 0; j < L; j++) {
                    int idx = i - j;
                    if (idx < 0 || installed[idx] || map[row][idx] != map[row][i])
                        return false;
                    installed[idx] = true;
                }
            }
            // 내리막일 경우
            else if (diff == -1) {
                for (int j = 1; j <= L; j++) {
                    int idx = i + j;
                    if (idx >= N || installed[idx] || map[row][idx] != map[row][i] - 1)
                        return false;
                    installed[idx] = true;
                }
            }
        }
        return true;
    }

    static boolean calCol(int col) {
        boolean[] installed = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int diff = map[i + 1][col] - map[i][col];
            // 인접한 높이가 2 이상일 경우 경사로 설치 불가능
            if (Math.abs(diff) >= 2)
                return false;
            // 오르막일 경우
            if (diff == 1) {
                for (int j = 0; j < L; j++) {
                    int idx = i - j;
                    if (idx < 0 || installed[idx] || map[idx][col] != map[i][col])
                        return false;
                    installed[idx] = true;
                }
            }
            // 내리막일 경우
            else if (diff == -1) {
                for (int j = 1; j <= L; j++) {
                    int idx = i + j;
                    if (idx >= N || installed[idx] || map[idx][col] != map[i][col] - 1)
                        return false;
                    installed[idx] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        answer = 0;

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            if (calRow(i))
                answer++;
            if (calCol(i))
                answer++;
        }

        System.out.println(answer);
    }
}