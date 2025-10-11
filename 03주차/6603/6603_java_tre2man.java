import java.io.*;
import java.util.*;

public class Main {
    static int k;
    static int[] s;
    static int[] a;

    static void backtrack(int now, int depth) {
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                System.out.print(a[i]);
                System.out.print(" ");
            }
            System.out.println();
            return;
        }

        for (int i = now; i < k; i++) {
            a[depth] = s[i];
            backtrack(i + 1, depth + 1);
        }
    }

    static void solution(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);

        while (true) {
            k = sc.nextInt();
            if (k == 0) {
                sc.close();
                System.exit(0);
            }
            s = new int[k];
            a = new int[k];
            for (int i = 0; i < k; i++) {
                s[i] = sc.nextInt();
            }
            backtrack(0, 0);
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        solution(args);
    }
}