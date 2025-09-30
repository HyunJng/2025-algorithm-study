import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int l = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            if (sx == ex && sy == ey) {
                System.out.println(0);
                continue;
            }

            // BFS
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[l][l];

            q.add(new int[]{sx, sy, 0});
            visited[sx][sy] = true;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                int cnt = cur[2];

                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || nx >= l || ny < 0 || ny >= l) continue;
                    if (visited[nx][ny]) continue;

                    if (nx == ex && ny == ey) {
                        System.out.println(cnt + 1);
                        q.clear();
                        break;
                    }

                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, cnt + 1});
                }
            }
        }
    }
}