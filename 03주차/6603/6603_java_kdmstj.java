import java.io.*;
import java.util.*;


public class Main {

    static int k;
    static int[] arr;
    static boolean[] visited;
    static final int MAX_COUNT = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            k = Integer.parseInt(st.nextToken());
            if (k == 0) break;

            arr = new int[k];
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[k];

            dfs(0, 0);

            System.out.println();
        }
    }

    private static void dfs(int startIdx, int count) {

        if (count == MAX_COUNT) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    sb.append(arr[i]).append(" ");
                }
            }
            System.out.println(sb.toString());
            return;
        }

        for (int i = startIdx; i < visited.length; i++) {
            visited[i] = true;
            dfs(i + 1, count + 1);
            visited[i] = false;
        }
    }
}
