import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int A, B;

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        A = input[0];
        B = input[1];

        find(4, 1);
        find(7, 1);

        System.out.println(count);
    }

    static void find(int num, int depth) {
        if (A <= num && num <= B) count++;

        if (num >= B) return;

        if(depth != 9) {
            find(num*10 + 4, depth + 1);
            find(num*10 + 7, depth + 1);
        }
    }
}