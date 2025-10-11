import java.io.*;

public class Main {
    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] num, op = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        num = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(input[i]);
        }

        input = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(input[i]);
        }

        backtrack(1, num[0]);
        System.out.println(max + "\n" + min);
    }

    static void backtrack(int idx, int n) {
        if (idx == N) {
            max = Math.max(max, n);
            min = Math.min(min, n);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) {
                op[i]--;
                backtrack(idx + 1, calc(n, num[idx], i));
                op[i]++;
            }
        }
    }

    static int calc(int a, int b, int op) {
        switch (op) {
            case 0 : return a + b;
            case 1 : return a - b;
            case 2 : return a * b;
            default : return a / b;
        }
    }
}
