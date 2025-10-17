import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] numbers;
    static final int LOTTO_COUNT = 6;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            if (K == 0) break;

            numbers = new int[K];

            for (int i = 0; i < K; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
            solution();
            sb.append("\n");
        }
        System.out.println(sb); //출력

    }

    private static void solution() {
        selected = new int[LOTTO_COUNT];
        dfs(0,0);
    }

    private static void dfs(int depth, int start) {
        if (depth == LOTTO_COUNT) {
            for(int i = 0; i < LOTTO_COUNT; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            selected[depth] = numbers[i];
            dfs(depth + 1, i + 1);
        }

    }
}
