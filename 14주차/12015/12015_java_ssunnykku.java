import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        List<Integer> lis = new ArrayList<>();

        for (int n : arr) {
            int index = Collections.binarySearch(lis, n);

            if (index < 0) {
                index = -(index + 1);
            }

            if (index == lis.size()) {
                lis.add(n);
            } else {
                lis.set(index, n);
            }

        }

        System.out.println(lis.size());

    }
}
