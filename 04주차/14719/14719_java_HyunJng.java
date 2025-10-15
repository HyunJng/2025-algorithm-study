import java.util.*;
import java.io.*;

public class Main {
    static int H, W;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        H = Integer.parseInt(inputs[0]);
        W = Integer.parseInt(inputs[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.print(solution());
    }

    public static int solution() {
        int left = 0, right = arr.length - 1;
        int leftMax = 0, rightMax = 0, result = 0;
        while (left < right) {
            if (arr[left] <= arr[right]) {
                leftMax = Math.max(leftMax, arr[left]);
                result += leftMax - arr[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, arr[right]);
                result += rightMax - arr[right];
                right--;
            }
        }
        return result;
    }
}