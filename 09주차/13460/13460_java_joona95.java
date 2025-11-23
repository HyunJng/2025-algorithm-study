import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static final char EMPTY = '.';
    private static final char WALL = '#';
    private static final char HOLE = 'O';
    private static final char RED = 'R';
    private static final char BLUE = 'B';
    private static final int LIMIT_COUNT = 10;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    public static int answer = Integer.MAX_VALUE;
    public static char[][] arr;
    public static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        int rx = -1, ry = -1, bx = -1, by = -1;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = str.charAt(j);
                if (arr[i][j] == RED) {
                    rx = i;
                    ry = j;
                    arr[i][j] = EMPTY;
                }
                if (arr[i][j] == BLUE) {
                    bx = i;
                    by = j;
                    arr[i][j] = EMPTY;
                }
            }
        }

        dfs(0, rx, ry, bx, by);

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int level, int rx, int ry, int bx, int by) {

        if (level >= answer) {
            return;
        }

        if (level == LIMIT_COUNT) {
            return;
        }

        for (int dir = 0; dir < dx.length; dir++) {

            int nrx = rx, nry = ry, nbx = bx, nby = by;
            boolean isRedInHole = false;
            boolean isBlueInHole = false;

            if (isRedFirst(dir, rx, ry, bx, by)) {

                isRedInHole = false;
                while (true) {
                    int tx = nrx + dx[dir];
                    int ty = nry + dy[dir];

                    if (tx < 0 || ty < 0 || tx >= n || ty >= m) {
                        break;
                    }
                    if (arr[tx][ty] == WALL) {
                        break;
                    }
                    if (arr[tx][ty] == HOLE) {
                        nrx = tx;
                        nry = ty;
                        isRedInHole = true;
                        break;
                    }
                    nrx = tx;
                    nry = ty;
                }

                isBlueInHole = false;
                while (true) {
                    int tx = nbx + dx[dir];
                    int ty = nby + dy[dir];

                    if (tx < 0 || ty < 0 || tx >= n || ty >= m) {
                        break;
                    }
                    if (arr[tx][ty] == WALL) {
                        break;
                    }
                    if (arr[tx][ty] == HOLE) {
                        nbx = tx;
                        nby = ty;
                        isBlueInHole = true;
                        break;
                    }
                    if (!isRedInHole && tx == nrx && ty == nry) {
                        break;
                    }
                    nbx = tx;
                    nby = ty;
                }

            } else {
                isBlueInHole = false;
                while (true) {
                    int tx = nbx + dx[dir];
                    int ty = nby + dy[dir];

                    if (tx < 0 || ty < 0 || tx >= n || ty >= m) {
                        break;
                    }
                    if (arr[tx][ty] == WALL) {
                        break;
                    }
                    if (arr[tx][ty] == HOLE) {
                        nbx = tx;
                        nby = ty;
                        isBlueInHole = true;
                        break;
                    }
                    nbx = tx;
                    nby = ty;
                }

                isRedInHole = false;
                while (true) {
                    int tx = nrx + dx[dir];
                    int ty = nry + dy[dir];

                    if (tx < 0 || ty < 0 || tx >= n || ty >= m) {
                        break;
                    }
                    if (arr[tx][ty] == WALL) {
                        break;
                    }
                    if (arr[tx][ty] == HOLE) {
                        nrx = tx;
                        nry = ty;
                        isRedInHole = true;
                        break;
                    }
                    if (!isBlueInHole && tx == nbx && ty == nby) {
                        break;
                    }
                    nrx = tx;
                    nry = ty;
                }
            }

            if (isBlueInHole) {
                continue;
            }

            if (isRedInHole) {
                answer = Math.min(answer, level + 1);
                continue;
            }

            dfs(level + 1, nrx, nry, nbx, nby);
        }
    }

    private static boolean isRedFirst(int dir, int rx, int ry, int bx, int by) {
        switch (dir) {
            case 0:
                return rx < bx;
            case 1:
                return ry > by;
            case 2:
                return rx > bx;
            case 3:
                return ry < by;
            default:
                return false;
        }
    }
}