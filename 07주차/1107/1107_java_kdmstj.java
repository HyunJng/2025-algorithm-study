package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int target;
    static int M;
    static boolean[] broken;
    static final int INITIAL_NUM = 100;

    public static void main(String[] args) throws IOException {
        input();

        // 숫자 버튼을 안쓰는 경우
        int answer = Math.abs(target - INITIAL_NUM);

        // 0 ~ 999999까지 검사
        for (int ch = 0; ch <= 999_999; ch++) {
            if (!canType(ch)) continue;           // 숫자 버튼으로 못 누르면 스킵
            int count = digitsLen(ch) + Math.abs(ch - target);
            answer = Math.min(answer, count);
        }

        System.out.println(answer);
    }

    static boolean canType(int ch) {
        if (ch == 0) return !broken[0];

        while (ch > 0) {
            if (broken[ch % 10]) return false;
            ch /= 10;
        }
        return true;
    }

    static int digitsLen(int ch) {
        if (ch == 0) return 1;

        int len = 0;
        while (ch > 0) {
            len++;
            ch /= 10;
        }
        return len;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nStr = br.readLine();
        target = Integer.parseInt(nStr);

        M = Integer.parseInt(br.readLine());
        broken = new boolean[10];

        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) broken[Integer.parseInt(st.nextToken())] = true;
        }
    }
}
