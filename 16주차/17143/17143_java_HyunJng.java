import java.io.*;
import java.util.*;

public class Main {
    static int R, C, M;
    static final int UP = 1, DOWN = 2, RIGHT = 3, LEFT = 4;
    static Shark[][] map, next;
    static List<Shark> sharks = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            Shark sh = new Shark(r, c, s, d, z);
            sharks.add(sh);
            map[r][c] = sh;
        }

        System.out.println(solution());
    }

    public static int solution() {
        int sum = 0;

        for (int col = 1; col <= C; col++) {
            sum += catchShark(col);
            moveAll();
        }

        return sum;
    }

    public static int catchShark(int col) {
        for (int row = 1; row <= R; row++) {
            if (map[row][col] != null) {
                Shark caught = map[row][col];
                map[row][col] = null;
                caught.alive = false;
                return caught.z;
            }
        }
        return 0;
    }

    public static void moveAll() {
        next = new Shark[R + 1][C + 1];
        List<Shark> nextList = new ArrayList<>();

        for (Shark sh : sharks) {
            if (!sh.alive) continue;

            Shark moved = moveOne(sh);

            Shark exist = next[moved.r][moved.c];
            if (exist == null) {
                next[moved.r][moved.c] = moved;
                nextList.add(moved);
            } else {
                if (exist.z < moved.z) {
                    exist.alive = false;
                    next[moved.r][moved.c] = moved;

                    for (int i = 0; i < nextList.size(); i++) {
                        if (nextList.get(i) == exist) {
                            nextList.set(i, moved);
                            break;
                        }
                    }
                    nextList.add(moved);
                    nextList.remove(moved);
                } else {
                    moved.alive = false;
                }
            }
        }

        sharks = new ArrayList<>();
        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                if (next[r][c] != null && next[r][c].alive) {
                    sharks.add(next[r][c]);
                }
            }
        }

        map = next;
    }

    public static Shark moveOne(Shark sh) {
        int r = sh.r, c = sh.c, s = sh.s, d = sh.d;

        if (d == UP || d == DOWN) {
            int cycle = (R - 1) * 2;
            int mv = (cycle == 0) ? 0 : s % cycle;

            for (int i = 0; i < mv; i++) {
                if (d == UP) {
                    if (r == 1) { d = DOWN; r++; }
                    else r--;
                } else {
                    if (r == R) { d = UP; r--; }
                    else r++;
                }
            }
        } else {
            int cycle = (C - 1) * 2;
            int mv = (cycle == 0) ? 0 : s % cycle;

            for (int i = 0; i < mv; i++) {
                if (d == RIGHT) {
                    if (c == C) { d = LEFT; c--; }
                    else c++;
                } else {
                    if (c == 1) { d = RIGHT; c++; }
                    else c--;
                }
            }
        }

        sh.r = r;
        sh.c = c;
        sh.d = d;
        return sh;
    }

    public static class Shark {
        int r, c, s, d, z;
        boolean alive = true;

        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
