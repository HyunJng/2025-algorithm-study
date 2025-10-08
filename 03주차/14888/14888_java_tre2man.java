import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] a;
    /**
     * 덧셈, 뺼셈, 곱셈, 나눗셈
     */
    static int[] cs;
    static int max;
    static int min;

    static void backtrack(int now, int depth) {
        if (depth == n) {
            max = Math.max(max, now);
            min = Math.min(min, now);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (cs[i] == 0) {
                continue;
            }
            cs[i]--;
            if (i == 0) {
                backtrack(now + a[depth], depth + 1);
            } else if (i == 1) {
                backtrack(now - a[depth], depth + 1);
            } else if (i == 2) {
                backtrack(now * a[depth], depth + 1);
            } else {
                backtrack(now / a[depth], depth + 1);
            }
            cs[i]++;
        }
    }

    static void solution() {
        backtrack(a[0], 1);
    }

    static void solution(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        n = sc.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        cs = new int[4];
        for (int i = 0; i < 4; i++) {
            cs[i] = sc.nextInt();
        }

        solution();
        System.out.println(max);
        System.out.println(min);
    }

    public static void main(String[] args) throws IOException {
        solution(args);
    }
}