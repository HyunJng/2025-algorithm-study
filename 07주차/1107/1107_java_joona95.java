import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static final int CURRENT_CHANNEL = 100;
    public static int n, m, answer = Integer.MAX_VALUE;
    public static Set<Integer> brokenButtons = new HashSet<>();

    public static void dfs(int level, int sum) {

        if (level == String.valueOf(n).length() + 1) {
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!brokenButtons.contains(i)) {
                int num = sum * 10 + i;
                answer = Math.min(answer, String.valueOf(num).length() + Math.abs(num - n));
                dfs(level + 1, num);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                brokenButtons.add(Integer.parseInt(st.nextToken()));
            }
        }

        dfs(0, 0);
        answer = Math.min(answer, Math.abs(n - CURRENT_CHANNEL));

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }
}