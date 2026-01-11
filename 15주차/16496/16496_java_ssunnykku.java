import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int len = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");

        String result = "";

        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        for (String num : arr) {
            result += num;
        }

        if (result.charAt(0) == '0') {
            System.out.println("0");
        } else {
            System.out.println(result);
        }

    }

}
