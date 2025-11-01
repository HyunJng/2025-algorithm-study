// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static class Piece {
        int r, c, direction;

        Piece(int r, int c, int direction) {
            this.r = r;
            this.c = c;
            this.direction = direction;
        }
    }

    static int N, K;
    static int[][] color;
    static ArrayList<Integer>[][] stacks;
    static Piece[] pieces;

    static final int[] dr = { 0, 0, 0, -1, 1 };
    static final int[] dc = { 0, 1, -1, 0, 0 };

    static int reverseDirection(int d) {
        if (d == 1)
            return 2;
        if (d == 2)
            return 1;
        if (d == 3)
            return 4;
        return 3;
    }

    static boolean outOrBlue(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= N)
            return true;
        return color[r][c] == 2;
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        color = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 스택 초기화
        stacks = new ArrayList[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                stacks[i][j] = new ArrayList<>();

        pieces = new Piece[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());
            pieces[i] = new Piece(r, c, direction);
            stacks[r][c].add(i); // 말 인덱스를 스택에
        }

        int answer = run();
        System.out.println(answer);
    }

    static int run() {
        for (int move = 1; move <= 1000; move++) {
            for (int i = 0; i < K; i++) {
                Piece p = pieces[i];
                int r = p.r;
                int c = p.c;
                int direction = p.direction;

                int nr = r + dr[direction];
                int nc = c + dc[direction];

                // 파랑 또는 범위 밖이면 방향 반전 후 한 번 더 시도
                if (outOrBlue(nr, nc)) {
                    direction = reverseDirection(direction);
                    pieces[i].direction = direction;
                    nr = r + dr[direction];
                    nc = c + dc[direction];
                    if (outOrBlue(nr, nc)) {
                        // 이동하지 않음
                        continue;
                    }
                }

                // 현재 칸 스택에서 i 말부터 위에 있는 것들을 이동
                List<Integer> fromStack = stacks[r][c];
                int idx = fromStack.indexOf(i);
                List<Integer> moving = new ArrayList<>(fromStack.subList(idx, fromStack.size()));
                // 원래 칸에서 제거
                fromStack.subList(idx, fromStack.size()).clear();

                // 빨강이면 뒤집기
                if (color[nr][nc] == 1) {
                    Collections.reverse(moving);
                }
                // 흰색은 진행함

                // 이동 반영
                for (int pid : moving) {
                    stacks[nr][nc].add(pid);
                    pieces[pid].r = nr;
                    pieces[pid].c = nc;
                }

                // 종료 조건 체크
                if (stacks[nr][nc].size() >= 4) {
                    return move;
                }
            }
        }
        return -1; // 1000 넘김
    }
}