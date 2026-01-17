import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int len = Integer.parseInt(br.readLine());

        int[][] lines = new int[len][2];

        for (int i = 0; i < len; i++) {
            int[] line = new int[2];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                line[j] = Integer.parseInt(st.nextToken());
            }
            lines[i] = line;
        }

        Arrays.sort(lines, (a, b) -> Integer.compare(a[0], b[0]));

        long sum = 0;
        int start = lines[0][0];
        int end = lines[0][1];
        for (int i = 1; i < lines.length; i++) {
            int left = lines[i][0];
            int right = lines[i][1];

            if (end > left && end < right) {
                end = right;
            } else if (end < left) {
                sum += end - start;
                start = left;
                end = right;
            } else if (end == left) {
                end = right;
            }

        }
        sum += end - start;

        System.out.println(sum);

    }
}