import java.io.*;
import java.util.*;

public class Main {
    static Long getAnswer(long length) {
        ArrayList<Long> dp = new ArrayList<>();
        dp.add(1L);
        dp.add(0L);
        dp.add(1L);

        if (length % 2 == 1) {
            return 0L;
        }

        if (length < 3) {
            return dp.get((int) length);
        }

        for (int i = 3; i <= length; i++) {
            if (i % 2 == 1) {
                dp.add(0L);
                continue;
            }
            long sum = 0L;
            for (int j = 0; j <= i - 2; j += 2) {
                long now = (dp.get(j) * dp.get(i - j - 2)) % 1000000007;
                sum = (sum + now) % 1000000007;
            }
            dp.add(sum);
        }
        return dp.get((int) length);
    }

    static void solution(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 파싱
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            long length = Long.parseLong(br.readLine());
            System.out.println(getAnswer(length));
        }

        br.close();
    }

    public static void main(String[] args) throws IOException {
        solution(args);
    }
}