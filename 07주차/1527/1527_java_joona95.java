import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int solution(int a, int b) {

        int answer = 0;
        for (int i = a; i <= b; i++) {
            if (isGeumMinSu(i)) {
                answer++;
            }
        }
        return answer;
    }

    private static boolean isGeumMinSu(int num) {
        while (num > 0) {
            int remain = num % 10;
            if (remain != 4 && remain != 7) {
                return false;
            }
            num /= 10;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(solution(a, b)));

        bw.flush();
        br.close();
        bw.close();
    }
}
