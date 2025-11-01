import java.io.*;
import java.util.*;

public class Main {
    static class Piece {
        int r, c, dir;
        Piece(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] color = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) color[i][j] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer>[][] board = new ArrayList[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board[i][j] = new ArrayList<>();

        Piece[] pieces = new Piece[K + 1];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            pieces[i] = new Piece(r, c, d);
            board[r][c].add(i);
        }

        int[] dr = {0, 0, 0, -1, 1};
        int[] dc = {0, 1, -1, 0, 0};

        int turn = 0;
        int answer = -1;

        while (turn <= 1000) {
            turn++;
            boolean finished = false;

            for (int i = 1; i <= K; i++) {
                Piece p = pieces[i];
                int r = p.r;
                int c = p.c;
                int dir = p.dir;

                ArrayList<Integer> stack = board[r][c];

                int idx = 0;
                for (int t = 0; t < stack.size(); t++) {
                    if (stack.get(t) == i) { idx = t; break; }
                }

                List<Integer> moving = new ArrayList<>();
                for (int t = idx; t < stack.size(); t++) moving.add(stack.get(t));

                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || color[nr][nc] == 2) {
                    int nd = reverseDir(dir);
                    pieces[i].dir = nd;
                    dir = nd;

                    nr = r + dr[dir];
                    nc = c + dc[dir];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || color[nr][nc] == 2) {
                        continue;
                    }
                }

                for (int t = stack.size() - 1; t >= idx; t--) {
                    stack.remove(t);
                }

                if (color[nr][nc] == 0) {
                    for (int id : moving) {
                        board[nr][nc].add(id);
                        pieces[id].r = nr;
                        pieces[id].c = nc;
                    }
                } else if (color[nr][nc] == 1) {
                    for (int t = moving.size() - 1; t >= 0; t--) {
                        int id = moving.get(t);
                        board[nr][nc].add(id);
                        pieces[id].r = nr;
                        pieces[id].c = nc;
                    }
                }

                if (board[nr][nc].size() >= 4) {
                    answer = turn;
                    finished = true;
                    break;
                }
            }

            if (finished) break;
            if (turn == 1000) break;
        }

        if (answer == -1 || answer > 1000) System.out.println(-1);
        else System.out.println(answer);
    }

    static int reverseDir(int d) {
        if (d == 1) return 2;
        if (d == 2) return 1;
        if (d == 3) return 4;
        if (d == 4) return 3;
        return d;
    }
}
