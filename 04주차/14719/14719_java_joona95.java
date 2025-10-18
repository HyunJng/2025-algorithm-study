import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public int solution(int h, int w, int[] arr) {

        int answer = 0;

        for (int i = 1; i < w - 1; i++) {
            int left = 0;
            for (int j = 0; j < i; j++) {
                left = Math.max(left, arr[j]);
            }

            int right = 0;
            for (int j = i + 1; j < w; j++) {
                right = Math.max(right, arr[j]);
            }

            if (arr[i] < left && arr[i] < right) {
                answer += Math.min(left, right) - arr[i];
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main main = new Main();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] arr = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = main.solution(h, w, arr);

        bw.write(String.valueOf(answer));
        bw.flush();
        br.close();
        bw.close();
    }
}