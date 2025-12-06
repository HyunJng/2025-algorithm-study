//package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int r, c, answer;
    static int[] dr = { -1, 0, 1 };
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < r; i++) {
            dfs(0, i);
        }
        System.out.println(answer);
    }

    static boolean dfs(int x, int y) {
        if (x == c - 1) {
            answer++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + 1;
            int ny = y + dr[i];

            if (nx < 0 || nx >= c || ny < 0 || ny >= r)
                continue;
            if (map[ny][nx] == 'x')
                continue;
            if (visited[ny][nx])
                continue;

            visited[ny][nx] = true;
            if (dfs(nx, ny)) {
                return true;
            }
        }
        return false;
    }
}