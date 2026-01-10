import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static int[] arr;
    public static int answerA, answerB;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();

        bw.write(answerA + " " + answerB);

        bw.flush();
        br.close();
        bw.close();
    }

    public static void solution() {

        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;
        int lt = 0, rt = n - 1;
        while (lt < rt) {
            int sum = arr[lt] + arr[rt];
            int abs = Math.abs(sum);
            if (abs < min) {
                min = abs;
                answerA = arr[lt];
                answerB = arr[rt];

                if (sum == 0) {
                    break;
                }
            }

            if (sum < 0) {
                lt++;
            } else {
                rt--;
            }
        }
    }
}