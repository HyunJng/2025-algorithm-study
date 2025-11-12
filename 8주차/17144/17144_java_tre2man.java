// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int r, c, t;
    // 공청기의 위치 x,y / 확산 계산값 임시저장
    static ArrayList<int[]> start, temp;
    static int[][] map;
    static final int[] dx = { 1, -1, 0, 0 };
    static final int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        start = new ArrayList<>();
        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    start.add(new int[] { j, i });
                }
            }
        }

        while (t-- > 0) {
            temp = new ArrayList<>();
            // 확산
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (map[i][j] <= 0)
                        continue;

                    int amount = map[i][j] / 5;
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];
                        if (nx < 0 || nx >= c || ny < 0 || ny >= r)
                            continue;
                        if (map[ny][nx] == -1)
                            continue;
                        temp.add(new int[] { nx, ny, amount });
                        count++;
                    }
                    map[i][j] -= amount * count;
                }
            }
            temp.forEach(el -> map[el[1]][el[0]] += el[2]);

            // 공청기 반시계 방향
            int x = start.get(0)[0], y = start.get(0)[1];
            int[] direction = new int[] { 1, 0 };
            int before = 0;
            while (true) {
                int nx = x + direction[0];
                int ny = y + direction[1];

                // 위로 가야할 경우
                if (nx >= c) {
                    direction = new int[] { 0, -1 };
                    continue;
                }
                ;
                // 좌측으로 가야할 경우
                if (ny < 0) {
                    direction = new int[] { -1, 0 };
                    continue;
                }
                // 아래로 가야할경우
                if (nx < 0) {
                    direction = new int[] { 0, 1 };
                    continue;
                }
                // 공청기 종료 지점일 경우
                if (map[ny][nx] == -1)
                    break;

                int swap = map[ny][nx];
                map[ny][nx] = before;
                before = swap;
                x = nx;
                y = ny;
            }

            // 공청기 시계 방향

            x = start.get(1)[0];
            y = start.get(1)[1];
            direction = new int[] { 1, 0 };
            before = 0;
            while (true) {
                int nx = x + direction[0];
                int ny = y + direction[1];

                // 아래로 가야할 경우
                if (nx >= c) {
                    direction = new int[] { 0, 1 };
                    continue;
                }
                // 좌측으로 가야할 경우
                if (ny >= r) {
                    direction = new int[] { -1, 0 };
                    continue;
                }
                // 위로 가야할 경우
                if (nx < 0) {
                    direction = new int[] { 0, -1 };
                    continue;
                }
                // 공청기 종료 지점일 경우
                if (map[ny][nx] == -1)
                    break;

                int swap = map[ny][nx];
                map[ny][nx] = before;
                before = swap;
                x = nx;
                y = ny;
            }
        }

        int answer = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0)
                    answer += map[i][j];
            }
        }
        System.out.println(answer);
    }
}