package example.org;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String line = br.readLine();
            int[] arr = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            int k = arr[0];

            if(k == 0) break;

            int[] s = Arrays.copyOfRange(arr, 1, arr.length);

            for (int i = 0; i <= k - 6; i++) {
                dfs(k, s, i, i, 1, new StringBuilder());
            }

            System.out.println();
        }
    }

    public static void dfs(int k, int[] s, int firstPos, int pos, int depth, StringBuilder result) {
        if(k - pos - 1 < 6 - depth) return;

        StringBuilder sb = new StringBuilder(result);

        if(depth != 6) {
            sb.append(s[pos]).append(" ");

            for(int i = 1; i <= k - 6 - firstPos + 1; i++) {
                dfs(k, s, firstPos, pos + i, depth + 1, sb);
            }
        }
        else {
            sb.append(s[pos]);
            System.out.println(sb.toString());
        }
    }
}