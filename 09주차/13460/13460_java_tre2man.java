// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int n, m, answer;
    static char[][] map;
    static final char WALL = '#', EMPTY = '.', RED = 'R', BLUE = 'B', HOLE = 'O';
    static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, MAX_MOVE = 10;
    static final int[] dx = { 0, 0, -1, 1 };
    static final int[] dy = { -1, 1, 0, 0 };
    static ArrayList<Integer> move;

    // 한 개의 구슬을 dir 방향으로 끝까지 굴림
    // 리턴: [최종 x, 최종 y, 구멍에 빠졌으면 1, 아니면 0]
    static int[] moveBall(int x, int y, int dir, char color, char[][] board) {
        int cx = x;
        int cy = y;

        board[cy][cx] = EMPTY;

        while (true) {
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];
            char next = board[ny][nx];

            if (next == WALL || next == RED || next == BLUE) {
                break;
            }

            if (next == HOLE) {
                return new int[] { nx, ny, 1 };
            }

            cx = nx;
            cy = ny;
        }

        board[cy][cx] = color;
        return new int[] { cx, cy, 0 };
    }

    static void moveBoard() {
        char[][] copyMap = new char[n][m];
        // red x,y, blue x,y
        int[] location = new int[4];

        // 맵 복사 및 초기 위치 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyMap[i][j] = map[i][j];
                if (map[i][j] == RED) {
                    location[0] = j;
                    location[1] = i;
                } else if (map[i][j] == BLUE) {
                    location[2] = j;
                    location[3] = i;
                }
            }
        }

        // 최대 10번까지 기울이기
        for (int i = 0; i < MAX_MOVE; i++) {
            int nowMove = move.get(i);

            if (answer != -1 && answer <= i)
                return;

            int dir = nowMove;
            boolean redFirst;
            // 어떤 구슬을 먼저 굴릴지 결정 (겹치는 이슈)
            switch (dir) {
                case UP:
                    redFirst = (location[1] < location[3]);
                    break;
                case DOWN:
                    redFirst = (location[1] > location[3]);
                    break;
                case LEFT:
                    redFirst = (location[0] < location[2]);
                    break;
                case RIGHT:
                default:
                    redFirst = (location[0] > location[2]);
                    break;
            }

            boolean redInHole = false;
            boolean blueInHole = false;

            // 실제 한 번 기울이는 로직
            if (redFirst) {
                int[] r = moveBall(location[0], location[1], dir, RED, copyMap);
                redInHole = (r[2] == 1);
                location[0] = r[0];
                location[1] = r[1];

                int[] b = moveBall(location[2], location[3], dir, BLUE, copyMap);
                blueInHole = (b[2] == 1);
                location[2] = b[0];
                location[3] = b[1];
            } else {
                int[] b = moveBall(location[2], location[3], dir, BLUE, copyMap);
                blueInHole = (b[2] == 1);
                location[2] = b[0];
                location[3] = b[1];

                int[] r = moveBall(location[0], location[1], dir, RED, copyMap);
                redInHole = (r[2] == 1);
                location[0] = r[0];
                location[1] = r[1];
            }

            if (blueInHole) {
                return;
            }

            if (redInHole) {
                int moveCount = i + 1; // 현재까지 기울인 횟수
                if (answer == -1 || answer > moveCount) {
                    answer = moveCount;
                }
                return;
            }
        }
    }

    static void backTrack(int depth) {
        if (depth == MAX_MOVE) {
            moveBoard();
            return;
        }
        for (int i = 0; i <= RIGHT; i++) {
            if (!move.isEmpty() && move.get(move.size() - 1) == i)
                continue;
            move.add(i);
            backTrack(depth + 1);
            move.remove(move.size() - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        move = new ArrayList<>();
        answer = -1;

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        backTrack(0);
        System.out.println(answer);
    }
}