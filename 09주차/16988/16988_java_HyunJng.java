import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static final int EMPTY = 0, MINE = 1, YOURS = 2;
    static List<int[]> empties = new ArrayList<>();
    static int[][] moving = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == EMPTY) {
                    empties.add(new int[]{x, y});
                }
            }
        }

        System.out.println(solution());
    }

    public static int solution() {
        int result = 0;

        for (int i = 0; i < empties.size(); i++) {
            int[] a = empties.get(i);
            int ax = a[0], ay = a[1];

            map[ay][ax] = MINE;
            for (int j = i + 1; j < empties.size(); j++) {
                int[] b = empties.get(j);
                int bx = b[0], by = b[1];

                map[by][bx] = MINE;
                result = Math.max(result, countKilled());
                map[by][bx] = EMPTY;
            }
            map[ay][ax] = EMPTY;
        }

        return result;
    }

    static int countKilled() {
        boolean[][] visited = new boolean[N][M];
        int total = 0;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == YOURS && !visited[y][x]) {
                    GroupInfo g = dfs(x, y, visited);
                    if (!g.metEmpty) {
                        total += g.size;
                    }
                }
            }
        }
        return total;
    }

    static class GroupInfo {
        int size;
        boolean metEmpty;

        GroupInfo(int size, boolean metEmpty) {
            this.size = size;
            this.metEmpty = metEmpty;
        }
    }

    static GroupInfo dfs(int x, int y, boolean[][] visited) {
        visited[y][x] = true;
        int size = 1;
        boolean metEmpty = false;

        for (int[] move : moving) {
            int nx = x + move[0];
            int ny = y + move[1];
            if (!isAvailable(nx, ny)) continue;

            if (map[ny][nx] == EMPTY) {
                metEmpty = true;
            } else if (map[ny][nx] == YOURS && !visited[ny][nx]) {
                GroupInfo child = dfs(nx, ny, visited);
                size += child.size;
                metEmpty = metEmpty | child.metEmpty;
            }
        }

        return new GroupInfo(size, metEmpty);
    }

    static boolean isAvailable(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }
}