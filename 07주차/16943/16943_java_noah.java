import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static boolean[] visited;
    static int B;
    static int best = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String A = st.nextToken();
        B = Integer.parseInt(st.nextToken());

        arr = new int[A.length()];
        visited = new boolean[A.length()];
        for (int i = 0; i < A.length(); i++) {
            arr[i] = A.charAt(i) - '0';
        }

        dfs(0, 0, arr.length);
        System.out.println(best);
    }

    static void dfs(int val, int depth, int len) {
        if (depth == len) {
            if (val < B) best = Math.max(best, val);
            return; 
        }

        for (int i = 0; i < len; i++) {
            if ((depth == 0 && arr[i] == 0) || visited[i]) continue;

            visited[i] = true;
            dfs(val * 10 + arr[i], depth + 1, len);
            visited[i] = false;
        }
    }
}
