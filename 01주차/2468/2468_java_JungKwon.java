import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] area;
    static boolean[][] visited;
    static int height;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        area = new int[N][N];

        for (int i = 0; i < N; i++) {
            area[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int maxHeight = Arrays
                .stream(area)
                .flatMapToInt(Arrays::stream)
                .max()
                .orElse(100);

        int result=0;
        for (height = 0; height <= maxHeight; height++) {
            int count = 0;
            visited = new boolean[N][N];
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (area[x][y] > height && !visited[x][y]) {
                        dfs(x, y);
                        count++;
                    }
                }
            }
            result = count > result ? count : result;
        }
        System.out.println(result);
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if (!visited[nx][ny] && area[nx][ny] > height) {
                    dfs(nx, ny);
                }
            }
        }
    }
}