import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String A;
    static int B;
    static int answer;
    static int[] remain;
    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = st.nextToken();
        B = Integer.parseInt(st.nextToken());

        remain = new int[10];
        for (int i = 0; i < A.length(); i++) {
            remain[A.charAt(i) - '0']++;
        }

        selected = new int[A.length()];
        answer = -1;

        dfs(0);

        System.out.println(answer);
    }

    private static void dfs(int count) {
        if (count == A.length()) {
            StringBuilder sb = new StringBuilder();
            for (int num : selected) {
                sb.append(num);
            }

            int C = Integer.parseInt(sb.toString());
            if (C < B) answer = Math.max(answer, C);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (i == 0 && count == 0) continue;

            if (remain[i] > 0) {
                remain[i]--;
                selected[count] = i;
                dfs(count + 1);
                remain[i]++;
            }
        }
    }
}
