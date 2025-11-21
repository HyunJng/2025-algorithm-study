import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static final int EMPTY = 0;
    public static final int CHECK = -1;
    public static final int WALL = 6;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    public static final List<CCTV> cctvs = new ArrayList<>();
    public static int answer = Integer.MAX_VALUE;
    public static int n, m;

    static class CCTV {
        private final int x;
        private final int y;

        public CCTV(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != EMPTY && arr[i][j] != WALL) {
                    cctvs.add(new CCTV(i, j));
                }
            }
        }

        dfs(0, arr);

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int level, int[][] arr) {



        if (level == cctvs.size()) {
            answer = Math.min(answer, countBlindSpots(arr));
            return;
        }

        CCTV cctv = cctvs.get(level);
        int x = cctv.x;
        int y = cctv.y;
        for (int k = 0; k < dx.length; k++) {
            int[][] copy = deepCopy(arr);
            switch (arr[x][y]) {
                case 1:
                    markCoverage(copy, x, y, k);
                    break;
                case 2:
                    markCoverage(copy, x, y, k);
                    markCoverage(copy, x, y, (k + 2) % 4);
                    break;
                case 3:
                    markCoverage(copy, x, y, k);
                    markCoverage(copy, x, y, (k + 1) % 4);
                    break;
                case 4:
                    for (int l = 0; l < dx.length; l++) {
                        if (l != k) {
                            markCoverage(copy, x, y, l);
                        }
                    }
                    break;
                case 5:
                    for (int l = 0; l < dx.length; l++) {
                        markCoverage(copy, x, y, l);
                    }
                    break;
            }
            dfs(level + 1, copy);
        }
    }

    private static int[][] deepCopy(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, copy[i], 0, arr[i].length);
        }
        return copy;
    }

    private static void markCoverage(int[][] arr, int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        while (isInBounds(nx, ny)) {
            if (arr[nx][ny] == WALL) {
                break;
            }
            if (arr[nx][ny] == EMPTY) {
                arr[nx][ny] = CHECK;
            }
            nx += dx[dir];
            ny += dy[dir];
        }
    }

    private static boolean isInBounds(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    private static int countBlindSpots(int[][] arr) {
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == EMPTY) {
                    answer++;
                }
            }
        }
        return answer;
    }
}