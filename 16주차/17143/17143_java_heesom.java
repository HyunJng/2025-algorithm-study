import java.io.*;
import java.util.*;

public class Main {

    static class Shark {
        int speed, direction, size;
    }

    static int R, C, M;
    static int score = 0;
    static Shark[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            Shark shark = new Shark();
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            shark.speed = Integer.parseInt(st.nextToken());
            shark.direction = Integer.parseInt(st.nextToken());
            shark.size = Integer.parseInt(st.nextToken());
            board[r - 1][c - 1] = shark;
        }

        for (int col = 0; col < C; col++) {
            fishing(col);
            move();
        }

        System.out.println(score);
    }

    static void fishing(int col) {
        for (int row = 0; row < R; row++) {
            if (board[row][col] != null) {
                score += board[row][col].size;
                board[row][col] = null;
                return;
            }
        }
    }

    static void move() {
        Shark[][] next = new Shark[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == null) continue;

                Shark shark = board[i][j];
                int cycle = shark.direction < 3 ? (R - 1) * 2 : (C - 1) * 2;
                int dist = shark.speed % cycle;
                int row = i;
                int col = j;

                for (int k = 0; k < dist; k++) {
                    if (shark.direction == 1) {
                        row--;
                        if (row < 0) {
                            shark.direction = 2;
                            row = 1;
                        }
                    } else if (shark.direction == 2) {
                        row++;
                        if (row > R - 1) {
                            shark.direction = 1;
                            row = R - 2;
                        }
                    } else if (shark.direction == 3) {
                        col++;
                        if (col > C - 1) {
                            shark.direction = 4;
                            col = C - 2;
                        }
                    } else {
                        col--;
                        if (col < 0) {
                            shark.direction = 3;
                            col = 1;
                        }
                    }
                }

                if (next[row][col] == null || next[row][col].size < shark.size) {
                    next[row][col] = shark;
                }
            }
        }

        board = next;
    }
}