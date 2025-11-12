// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int n, m, answer;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(Character.toString(line.charAt(j)));
            }
        }

        answer = 1;
        // 정사각형의 한 변의 길이
        for(int i = 0; i < Math.min(n, m); i++) {
            // 시작 x 좌표
            for(int j = 0; j < n; j++) {
                // 시작 y 좌표
                for(int k = 0; k < m; k++) {
                    // 좌표 벗어나면 탐색 종료
                    if (j + i >= n || k + i >= m) continue;
                    int start = map[j][k];
                    if (start != map[j + i][k]) continue;
                    if (start != map[j][k + i]) continue;
                    if (start != map[j + i][k + i]) continue;
                    answer = Math.max(answer, (i + 1) * (i + 1));
                }
            }
        }

        System.out.println(answer);
    }
}