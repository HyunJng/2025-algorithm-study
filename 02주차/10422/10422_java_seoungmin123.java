import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int MAX = 2500; // 입력범위 5000 -> 괄호/2
    static int DIVIDED = 1000000007;
    static long[] C = new long[MAX+1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        preCalculate(); //카탈란 수 계산

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int L = Integer.parseInt(br.readLine());
            output(L); //출력
        }
    }

    private static void output(int L) {
        if (L % 2 == 1) {
            System.out.println(0);
        } else {
            System.out.println(C[L/2]);
        }
    }

    private static void preCalculate() {
        C[0] = 1; //빈문자열
        for (int i = 1; i <= MAX; i++) {
            for (int j = 0; j < i; j++) {
                C[i] += C[j] * C[i-1-j]; //카탈란 수 점화식..
                C[i] %= DIVIDED;
            }
        }
    }
}