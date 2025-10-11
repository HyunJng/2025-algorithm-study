package example.org;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] arr;
    static int[] operators;

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        operators = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        backtracking(1, arr[0]);

        System.out.println(max);
        System.out.println(min);
    }

    public static void backtracking(int depth, int currentValue) {
        if(depth == N) {
            max = Math.max(max, currentValue);
            min = Math.min(min, currentValue);
            return;
        }

        for(int i = 0; i < operators.length; i++) {
            if(operators[i] > 0) {
                operators[i]--;
                int newValue = currentValue;

                switch(i) {
                    case 0: // +
                        newValue += arr[depth];
                        break;
                    case 1: // -
                        newValue -= arr[depth];
                        break;
                    case 2: // *
                        newValue *= arr[depth];
                        break;
                    case 3: // /
                        newValue /= arr[depth];
                        break;
                }

                backtracking(depth + 1, newValue);
                operators[i]++;
            }
        }
    }
}