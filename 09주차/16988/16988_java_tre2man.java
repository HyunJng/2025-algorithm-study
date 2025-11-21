// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int n, m, answer;
    static int[][] map;
    static boolean[][] visited;
    static final int MAX_DOLL_NUM = 2;
    static final int[] dx = new int[] { -1, 1, 0, 0 };
    static final int[] dy = new int[] { 0, 0, -1, 1 };

    static boolean hasEmpty;

    static int dfs(int x, int y) {
        visited[y][x] = true;
        int result = 1; // 현재 돌 1개

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[ny][nx])
                continue;
            if (map[ny][nx] == 0) {
                hasEmpty = true;
                continue;
            }
            if (map[ny][nx] == 1)
                continue;
            if (map[ny][nx] == 2) {
                result += dfs(nx, ny);
            }
        }

        return result;
    }

    static void backTrack(int depth) {
        if (depth == MAX_DOLL_NUM) {
            visited = new boolean[n][m];
            int total = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && map[i][j] == 2) {
                        hasEmpty = false;
                        int cnt = dfs(j, i);
                        if (!hasEmpty) {
                            total += cnt;
                        }
                    }
                }
            }
            answer = Math.max(answer, total);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    backTrack(depth + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        answer = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTrack(0);
        System.out.println(answer);
    }
}