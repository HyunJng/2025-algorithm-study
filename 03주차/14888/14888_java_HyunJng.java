import java.util.*;
import java.io.*;

public class Main {
    static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static int[] operator, numbers;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        operator = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // + - * /
        solution(1, numbers[0]);

        System.out.print(max + "\n" + min);
    }

    public static void solution(int depth, int value) {
        if (depth == n) {
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] == 0) continue;
            operator[i]--;
            solution(depth + 1, calculate(value, numbers[depth], i));
            operator[i]++;
        }
    }

    public static int calculate(int a, int b, int j) {
        int result = 0;
        switch(j){
            case 0 : result = a + b; break;
            case 1 : result = a - b; break;
            case 2 : result =  a * b; break;
            default : result = a / b;
        }
        return result;
    }
}