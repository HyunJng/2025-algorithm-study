import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        Integer[] arr = new Integer[num];
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        List<Integer> array = new ArrayList<Integer>(Arrays.asList(arr));

        int sum = Integer.MAX_VALUE;
        int leftIdx = 0;
        int rightIdx = num - 1;

        array.sort((a, b) -> a - b);

        ArrayList<Integer> result = new ArrayList<>();

        while (leftIdx < rightIdx) {
            int left = array.get(leftIdx);
            int right = array.get(rightIdx);

            if (Math.abs(left + right) < sum) {
                sum = Math.abs(left + right);
                result.clear();
                result.add(left);
                result.add(right);
            }

            if (left + right < 0) {
                leftIdx++;
            } else if (left + right > 0) {
                rightIdx--;
            } else {
                break;
            }
        }

        System.out.println(result.get(0) + " " + result.get(1));

    }

}
