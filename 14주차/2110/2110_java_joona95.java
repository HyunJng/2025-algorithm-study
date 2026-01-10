import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int n, c;
    public static int[] arr;
    public static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        solution();

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    public static void solution() {

        Arrays.sort(arr);

        int lt = 1, rt = arr[n - 1] - arr[0];
        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            int count = countInstallHouses(mid);

            if (count >= c) {
                answer = mid;
                lt = mid + 1;
            } else {
                rt = mid - 1;
            }
        }
    }

    private static int countInstallHouses(int length) {
        int idx = 0;
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] - arr[idx] >= length) {
                idx = i;
                count++;
            }
        }
        return count;
    }
}