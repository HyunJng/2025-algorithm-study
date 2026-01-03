import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int CLEANED = 2;
    public static final int[] dx = {-1, 0, 1, 0};
    public static final int[] dy = {0, 1, 0, -1};
    public static int n, m;
    public static int[][] arr;
    public static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, d);

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int x, int y, int d) {

        if (arr[x][y] == EMPTY) {
            arr[x][y] = CLEANED;
            answer++;
        }

        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4;
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }
            if (arr[nx][ny] == EMPTY) {
                dfs(nx, ny, d);
                return;
            }
        }

        int nx = x - dx[d];
        int ny = y - dy[d];

        if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
            return;
        }
        if (arr[nx][ny] == WALL) {
            return;
        }

        dfs(nx, ny, d);
    }
}