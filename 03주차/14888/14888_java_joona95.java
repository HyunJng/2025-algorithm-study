import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static final int SIGN_COUNT = 4;
    private static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    private static int n;
    private static int[] arr;
    private static int[] signs;

    public void dfs(int v, int result) {

        if (v == n - 1) {
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        for (int i = 0; i < SIGN_COUNT; i++) {
            if (signs[i] > 0) {
                signs[i]--;
                switch (i) {
                    case 0:
                        dfs(v + 1, result + arr[v + 1]);
                        break;
                    case 1:
                        dfs(v + 1, result - arr[v + 1]);
                        break;
                    case 2:
                        dfs(v + 1, result * arr[v + 1]);
                        break;
                    case 3:
                        dfs(v + 1, result / arr[v + 1]);
                        break;
                }
                signs[i]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main main = new Main();


        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        signs = new int[SIGN_COUNT];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < SIGN_COUNT; i++) {
            signs[i] = Integer.parseInt(st.nextToken());
        }

        main.dfs(0, arr[0]);

        bw.write(max + "\n" + min);
        bw.flush();
        br.close();
        bw.close();
    }
}