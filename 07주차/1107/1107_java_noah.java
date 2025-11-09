import java.io.*;
import java.util.*;

public class Main {
    static boolean[] broken = new boolean[10];
    static int target;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        target = Integer.parseInt(br.readLine());
        int brokenCount = Integer.parseInt(br.readLine());

        if (brokenCount > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < brokenCount; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        answer = Math.abs(target - 100);

        for (int length = 1; length <= 6; length++) {
            dfs(0, length, 0);
        }

        System.out.println(answer);
    }

    static void dfs(int depth, int limit, int value) {
        if (depth == limit) {
            int press = limit + Math.abs(value - target);
            answer = Math.min(answer, press);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (broken[i]) continue;
            if (depth == 0 && i == 0 && limit > 1) continue;

            dfs(depth + 1, limit, value * 10 + i);
        }
    }
}
