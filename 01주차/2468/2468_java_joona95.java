import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int MAX_HEIGHT = 100;
    private static int MOVE_CNT = 4;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int[][] graph;
    private static boolean[][] visited;

    public int solution() {
        int answer = 1;

        for (int height = 1; height <= MAX_HEIGHT; height++) {
            initVisited(height);

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;
                        dfs(i, j);
                        cnt++;
                    }
                }
            }

            answer = Math.max(answer, cnt);
        }

        return answer;
    }

    private void dfs(int x, int y) {
        for (int i = 0; i < MOVE_CNT; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }

    private void initVisited(int height) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = graph[i][j] <= height;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Main main = new Main();

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        visited = new boolean[N][N];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, graph[i][j]);
            }
        }
        MAX_HEIGHT = max;

        bw.write(String.valueOf(main.solution()));
        bw.flush();

        br.close();
        bw.close();
    }
}
