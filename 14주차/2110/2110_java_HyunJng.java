import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static long[] house;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        house = new long[N];

        for (int i = 0; i < N; i++) {
            house[i] = Long.parseLong(br.readLine());
        }

        System.out.println(solution());
    }

    public static long solution() {
        Arrays.sort(house);

        long minDistance = 1, maxDistance = house[N - 1] - house[0];
        long result = 0;

        while (minDistance <= maxDistance) {
            long mid = (minDistance + maxDistance) / 2;

            if (canInstall(mid)) {
                result = mid;
                minDistance = mid + 1;
            } else {
                maxDistance = mid - 1;
            }
        }
        return result;
    }

    public static boolean canInstall(long dist) {
        int count = 1;
        long last = house[0];

        for (int i = 1; i < N; i++) {
            if (house[i] - last >= dist) {
                count++;
                last = house[i];
                if(count >= C) return true;
            }
        }
        return false;
    }

}