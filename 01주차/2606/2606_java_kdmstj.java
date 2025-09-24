import java.util.*;
import java.io.*;

public class Main {
    static int N, P;
    static boolean[][] connected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        connected = new boolean[N + 1][N + 1];
        for (int p = 0; p < P; p++) {
            String[] inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);

            connected[a][b] = true;
            connected[b][a] = true;
        }

        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        int answer = 0;

        while (!queue.isEmpty()) {
            int num = queue.poll();

            for (int i = 1; i <= N; i++) {
                if (!visited[i] && connected[num][i]) {
                    visited[i] = true;
                    queue.add(i);
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}
