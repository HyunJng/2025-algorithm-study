import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int COUNT;
    static int[] NUMBERS;
    static int[] OPERATOR = new int[4];
    static int MAX = -1_000_000_000;
    static int MIN = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        COUNT = Integer.parseInt(br.readLine());
        NUMBERS = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        OPERATOR = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dfs(1, NUMBERS[0]);
        System.out.println(MAX);
        System.out.println(MIN);
    }

    static void dfs(int idx, int current) {
        if (idx == COUNT) {
            MAX = Math.max(MAX, current);
            MIN = Math.min(MIN, current);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if(OPERATOR[i] > 0) {
                OPERATOR[i]--;
                dfs(idx + 1, calculate(current, NUMBERS[idx], i));
                OPERATOR[i]++;
            }
        }
    }

    static int calculate(int a, int b, int op) {
        return switch (op) {
            case 0 -> a + b;
            case 1 -> a - b;
            case 2 -> a * b;
            case 3 -> {
                if (a < 0) {
                    yield -(-a / b);
                } else {
                    yield a / b;
                }
            }
            default -> throw new IllegalArgumentException("잘못된 연산자 코드");
        };
    }
}