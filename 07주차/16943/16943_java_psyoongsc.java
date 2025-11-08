import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int A, B;
    static int[] digits = new int[10];

    static int A_length = 0;

    static int maxNum = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        A = input[0];
        B = input[1];

        for (int i = 1; A / 10 != 0; i++) {
            digits[A % 10]++;
            A /= 10;
            A_length++;
        }
        digits[A % 10]++;
        A_length++;

        for (int i = 1; i < 10; i++) {
            if (digits[i] != 0) {
                digits[i]--;
                backtrack(i, 1, digits);
                digits[i]++;
            }
        }

        System.out.println(maxNum);
    }

    static void backtrack(int num, int depth, int[] remainDigits) {
        for (int i = 0; i < 10; i++) {
            if (remainDigits[i] == 0) continue;

            int curNum = num * 10 + i;

            if (A_length == depth + 1) {
                if(curNum < B) maxNum = Math.max(maxNum, curNum);
                break;
            }

            remainDigits[i]--;
            backtrack(curNum, depth + 1, remainDigits);
            remainDigits[i]++;
        }
    }
}