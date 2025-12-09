import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static final char BUILDING = 'x';
    public static final int[] directions = {-1, 0, 1};
    public static int r, c;
    public static int answer = 0;
    public static boolean[][] visited;
    public static boolean isFound;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                if (ch == BUILDING) {
                    visited[i][j] = true;
                }
            }
        }


        for (int i = 0; i < r; i++) {
            isFound = false;
            visited[i][0] = true;
            dfs(i, 0);
        }

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int row, int col) {

        if (col == c - 1) {
            answer++;
            isFound = true;
            return;
        }

        for (int dir : directions) {
            int nr = row + dir;
            int nc = col + 1;
            if (nr >= 0 && nr < r && !visited[nr][nc]) {
                if (!isFound) {
                    visited[nr][nc] = true;
                    dfs(nr, nc);
                }
            }
        }
    }
}