import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static int[] arr;
    public static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(solution()));

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution() {

        dp = new int[n + 1];
        int len = 0;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > dp[len]) {
                len++;
                dp[len] = arr[i];
            } else {
                idx = findReplacePosition(0, len, arr[i]);
                dp[idx] = arr[i];
            }
        }
        return len;
    }

    private static int findReplacePosition(int lt, int rt, int num) {

        while (lt < rt) {
            int mid = (lt + rt) / 2;
            if (dp[mid] < num) {
                lt = mid + 1;
            } else {
                rt = mid;
            }
        }
        return rt;
    }
}