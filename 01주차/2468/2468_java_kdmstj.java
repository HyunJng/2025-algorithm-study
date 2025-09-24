import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        int maxDepth = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxDepth = Math.max(map[i][j], maxDepth);
            }
        }

        int max_count = 0;
        for(int depth = 0; depth <= maxDepth; depth++){

            boolean[][] visited = new boolean[N][N];
            int count = 0;
            for(int i = 0; i <N; i++){
                for(int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > depth) {
                        count++;
                        Queue<int[]> queue = new LinkedList<>();
                        queue.add(new int[]{i, j});
                        visited[i][j] = true;

                        while (!queue.isEmpty()) {
                            int[] node = queue.poll();

                            for (int d = 0; d < 4; d++) {
                                int nX = node[0] + dx[d];
                                int nY = node[1] + dy[d];

                                if (!isOutOfBound(nX, nY) && !visited[nX][nY] && map[nX][nY] > depth) {
                                    queue.add(new int[]{nX, nY});
                                    visited[nX][nY] = true;
                                }
                            }
                        }
                    }
                }
            }

            max_count = Math.max(max_count, count);
        }

        System.out.println(max_count);
    }

    private static boolean isOutOfBound(int x, int y){
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}
