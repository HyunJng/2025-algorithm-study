import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static char[][] map;
    static int holeX, holeY;
    static int[] dx = {-1, 1, 0, 0}; //좌, 우, 상, 하
    static int[] dy = {0, 0, -1, 1};
    static boolean[][][][] checked;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        checked = new boolean[N][M][N][M];
        map = new char[N][M];
        int redX = 0, redY = 0, blueX = 0, blueY = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') {
                    redX = i;
                    redY = j;
                }

                if (map[i][j] == 'B') {
                    blueX = i;
                    blueY = j;
                }

                if (map[i][j] == 'O') {
                    holeX = i;
                    holeY = j;
                }

            }
        }

        bfs(redX, redY, blueX, blueY);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }

    static void bfs(int redX, int redY, int blueX, int blueY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{redX, redY, blueX, blueY, 0});
        checked[redX][redY][blueX][blueY] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();

            if (pos[4] >= 10) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nRx = pos[0];
                int nRy = pos[1];
                int nBx = pos[2];
                int nBy = pos[3];


                //빨간 구슬 이동
                while (map[nRx + dx[i]][nRy + dy[i]] != '#') {
                    nRx += dx[i];
                    nRy += dy[i];
                    if (map[nRx][nRy] == 'O') break;
                }

                //파란 구슬 이동
                while (map[nBx + dx[i]][nBy + dy[i]] != '#') {
                    nBx += dx[i];
                    nBy += dy[i];
                    if (map[nBx][nBy] == 'O') break;
                }

                if (nBx == holeX && nBy == holeY) {
                    continue;
                }


                if (nRx == holeX && nRy == holeY) {
                    answer = Math.min(answer, pos[4] + 1);
                    return;
                }

                if (nRx == nBx && nRy == nBy && map[nRx][nRy] != 'O') {
                    int red_move = Math.abs(nRx - pos[0]) + Math.abs(nRy - pos[1]);
                    int blue_move = Math.abs(nBx - pos[2]) + Math.abs(nBy - pos[3]);

                    if (blue_move < red_move) {
                        nRx -= dx[i];
                        nRy -= dy[i];
                    } else {
                        nBx -= dx[i];
                        nBy -= dy[i];
                    }
                }

                if (!checked[nRx][nRy][nBx][nBy]) {
                    checked[nRx][nRy][nBx][nBy] = true;
                    queue.add(new int[]{nRx, nRy, nBx, nBy, pos[4] + 1});
                }

            }
        }
    }
}
