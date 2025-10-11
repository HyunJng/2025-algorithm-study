import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String END_COMMAND = "0";
    private static final StringBuilder sb = new StringBuilder();
    private static int n;
    private static int[] arr;

    public void dfs(int level, int v, String s) {

        if (level == LOTTO_NUMBER_COUNT - 1) {
            sb.append(s).append(arr[v]).append("\n");
            return;
        }

        for (int i = v + 1; i < n; i++) {
            dfs(level + 1, i, s + arr[v] + " ");
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main main = new Main();

        while (true) {
            String s = br.readLine();
            if (s.equals(END_COMMAND)) {
                break;
            }

            StringTokenizer st = new StringTokenizer(s);
            n = Integer.parseInt(st.nextToken());
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i <= n - LOTTO_NUMBER_COUNT; i++) {
                main.dfs(0, i, "");
            }

            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}