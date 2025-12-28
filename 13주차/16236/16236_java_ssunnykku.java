import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int sharkSize = 2;
    static int eatCount = 0;
    static int totalTime = 0;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static class Fish {
        int r, c, dist;
        Fish(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int sharkR = 0, sharkC = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkR = i;
                    sharkC = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            Fish fish = bfs(sharkR, sharkC);
            if (fish == null) break;

            totalTime += fish.dist;
            sharkR = fish.r;
            sharkC = fish.c;

            eatCount++;
            map[fish.r][fish.c] = 0;

            if (eatCount == sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
        }

        System.out.println(totalTime);
    }

    static Fish bfs(int sr, int sc) {
        boolean[][] visited = new boolean[N][N];
        Queue<Fish> queue = new LinkedList<>();
        queue.offer(new Fish(sr, sc, 0));
        visited[sr][sc] = true;

        List<Fish> canEat = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Fish cur = queue.poll();

            if (cur.dist > minDist) continue;

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dx[d];
                int nc = cur.c + dy[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] > sharkSize) continue;

                visited[nr][nc] = true;
                int nd = cur.dist + 1;

                if (map[nr][nc] != 0 && map[nr][nc] < sharkSize) {
                    if (nd <= minDist) {
                        minDist = nd;
                        canEat.add(new Fish(nr, nc, nd));
                    }
                }

                queue.offer(new Fish(nr, nc, nd));
            }
        }

        if (canEat.isEmpty()) return null;

        canEat.sort((a, b) -> {
            if (a.dist != b.dist) return a.dist - b.dist;
            if (a.r != b.r) return a.r - b.r;
            return a.c - b.c;
        });

        return canEat.get(0);
    }
}
