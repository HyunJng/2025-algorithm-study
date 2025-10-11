import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int LOTTO_NUMBER = 6;
    static int INPUT_COUNT;
    static int[] INPUT_NUMBER;
    static int DEPTH = 0;
    static int[] selected = new int[LOTTO_NUMBER];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] split = br.readLine().split(" ");
            INPUT_COUNT = Integer.parseInt(split[0]);
            if (INPUT_COUNT == 0) {
                break;
            }

            INPUT_NUMBER = new int[INPUT_COUNT +1];
            for (int i = 1; i <= INPUT_COUNT; i++) {
                INPUT_NUMBER[i-1] = Integer.parseInt(split[i]);
            }

            dfs(0);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int start) {
        if (DEPTH == LOTTO_NUMBER) {
            for (int i = 0; i < LOTTO_NUMBER; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < INPUT_COUNT; i++) {
            selected[DEPTH] = INPUT_NUMBER[i];
            DEPTH++;
            dfs(i+1);
            DEPTH--;
        }
    }
}