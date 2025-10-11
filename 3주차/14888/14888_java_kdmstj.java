import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long[] arr;
    static int[] ops;
    static long min = Long.MAX_VALUE;
    static long max = Long.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        input();

        simulation(arr[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    private static void simulation(long result, int depth) {
        if (depth == N) {
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        for (int op = 0; op < 4; op++) {
            if (ops[op] == 0) continue;
            ops[op]--;
            long next = apply(op, result, arr[depth]);
            simulation(next, depth + 1);
            ops[op]++;
        }
    }

    private static long apply(int op, long a, long b) {
        switch (op) {
            case 0:
                return a + b;
            case 1:
                return a - b;
            case 2:
                return a * b;
            case 3:
                return towardZeroDiv(a, b);
            default:
                throw new IllegalArgumentException("unknown op");
        }
    }

    private static long towardZeroDiv(long a, long b) {
        long result = Math.abs(a) / Math.abs(b);

        if (a < 0) return -result;

        return result;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        ops = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
