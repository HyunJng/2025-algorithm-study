package example.org;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());
        String[] numbers = br.readLine().split(" ");
        int[] arr = new int[count];

        for (int i = 0; i < count; i++) {
            arr[i] = Integer.parseInt(numbers[i]);
        }

        int [] sumArr;
        sumArr = arr.clone();

        for(int i = 0; i < count - 1; i++) {
            for(int j = i + 1; j < count; j++) {
                if(arr[i] < arr[j]) {
                    sumArr[j] = Math.max(sumArr[j], sumArr[i] + arr[j]);
                }
            }
        }

        int max = sumArr[0];
        for(int i = 1; i < count; i++) {
            if(max < sumArr[i]) {
                max = sumArr[i];
            }
        }

        System.out.println(max);
    }
}