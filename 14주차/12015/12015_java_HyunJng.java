import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }

    public static int solution() {
        int[] tails = new int[N];
        int size = 0;

        for (int x : arr) {
            int idx = lowerBound(tails, size, x);
            tails[idx] = x;
            if (idx == size) size++;
        }

        return size;
    }
    static int lowerBound(int[] tails, int size, int x) {
        int left = 0, right = size;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (tails[mid] >= x) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}
