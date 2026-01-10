import java.io.*;
import java.util.*;

public class Main {
    static int T;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            bw.append(String.valueOf(solution(n, arr)));
            bw.append("\n");
        }
        bw.flush();
        bw.close();
    }

    public static long solution(int n, int[] arr) {
        long sum = 0;
        long max = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            long target = arr[i];
            if (target > max) {
                max = target;
            } else {
                sum += max - target;
            }
        }
        return sum;
    }
}