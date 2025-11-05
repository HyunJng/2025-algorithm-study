import java.io.*;
import java.util.*;

public class Main {
    static final int START = 100;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int target = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        boolean[] broken = new boolean[10];
        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        int ans = Math.abs(target - START);

        for (int x = 0; x <= 1_000_000; x++) {
            int d = digitsIfTypeable(x, broken);
            if (d >= 0) {
                ans = Math.min(ans, d + Math.abs(x - target));
            }
        }

        System.out.println(ans);
    }

    static int digitsIfTypeable(int x, boolean[] broken) {
        if (x == 0) return broken[0] ? -1 : 1;
        int cnt = 0;
        while (x > 0) {
            if (broken[x % 10]) return -1;
            cnt++;
            x /= 10;
        }
        return cnt;
    }
}