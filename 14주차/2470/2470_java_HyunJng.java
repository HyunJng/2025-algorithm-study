import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        System.out.println(solution());
    }

    public static String solution() {
        int left = 0, right = N - 1;
        long similarZero = Long.MAX_VALUE;
        int[] result = new int[2];

        Arrays.sort(arr);

        while (left < right) {
            long temp = Math.abs(arr[left] + arr[right]);
            if (temp < similarZero) {
                similarZero = temp;
                result[0] = left;
                result[1] = right;
            }
            if(arr[left] + arr[right] < 0) {
                left++;
            } else {
                right--;
            }
        }

        return arr[result[0]] + " " + arr[result[1]];
    }
}