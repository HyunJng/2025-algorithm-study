import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    static int count = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        solution(1, 0);
        System.out.println(count);
    }

    public static void solution(int row, int depth) {
        if (depth == n) {
            count++;
            return;
        }

        for (int col = 1; col <= n; col++) {
            boolean impossible = false;
            for (int findRow = 1; findRow <= n; findRow++) {
                if (col == arr[findRow] || arr[findRow] != 0 && Math.abs(row - findRow) == Math.abs(col - arr[findRow])) {
                    impossible = true; break;
                }
            }
            if(impossible) continue;
            arr[row] = col;
            solution(row + 1, depth + 1);
            arr[row] = 0;
        }
    }
}