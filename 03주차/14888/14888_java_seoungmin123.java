import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int maxValue = Integer.MIN_VALUE;
    static int minValue = Integer.MAX_VALUE;

    static int[] numbers;
    static int[] operators;
    static int operatorsNum = 4;
    static int N ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[N]; //숫자
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        operators = new int[operatorsNum]; // + - * / 순서
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < operatorsNum; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        dfs(numbers[0], 1);

        System.out.println(maxValue);
        System.out.println(minValue);

    }

    private static void dfs(int result, int depth){
        if(depth == N){
            maxValue = Math.max(maxValue, result);
            minValue = Math.min(minValue, result);
            return;
        }

        for (int i = 0; i < operatorsNum; i++) {
            if (operators[i] > 0) {
                operators[i]--;

                if (i == 0) { // 덧셈
                    dfs(result + numbers[depth], depth + 1);
                } else if (i == 1) { // 뺄셈
                    dfs(result - numbers[depth], depth + 1);
                } else if (i == 2) { // 곱셈
                    dfs(result * numbers[depth], depth + 1);
                } else { // 나눗셈
                    dfs(result / numbers[depth], depth + 1);
                }

                operators[i]++;
            }
        }

    }
}
