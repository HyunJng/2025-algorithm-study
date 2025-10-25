import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main_1244 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] switches = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            int gender = Integer.parseInt(input[0]);
            int num = Integer.parseInt(input[1]);

            if (gender == 1) {
                for (int j = num; j <= N; j += num) {
                    switches[j - 1] = 1 - switches[j - 1];
                }
            } else {
                int left = num - 1;
                int right = num - 1;
                while (left > 0 && right < N - 1 && switches[left - 1] == switches[right + 1]) {
                    left--;
                    right++;
                }
                for (int j = left; j <= right; j++) {
                    switches[j] = 1 - switches[j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        IntStream.range(0, N).forEach(i -> {
            sb.append(switches[i])
                    .append(" ")
                    .append((i + 1) % 20 == 0 ? "\n" : "");
        });
        System.out.println(sb);
    }
}