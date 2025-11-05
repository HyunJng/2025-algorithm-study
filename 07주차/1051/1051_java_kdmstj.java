import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int answer = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int size = 0; size < Math.min(N, M); size++) {
                    if (i + size >= N || j + size >= M) break;
                    if (map[i][j] == map[i + size][j] && map[i+size][j] == map[i][j + size] && map[i][j+size] == map[i + size][j + size]) {
                        answer = Math.max(answer, (size + 1) * (size + 1));
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
