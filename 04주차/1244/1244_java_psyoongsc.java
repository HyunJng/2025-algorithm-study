import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static boolean[] switches;

    static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        switches = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .mapToObj(i -> i == 1)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        list -> {
                            boolean[] arr = new boolean[list.size()];
                            for (int i = 0; i < list.size(); i++) arr[i] = list.get(i);
                            return arr;
                        }));
        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if(input[0] == 1) {
                int n = 0;
                while(true) {
                    int pos = input[1] + input[1] * n;
                    if(pos > N) break;

                    switches[pos - 1] = !switches[pos - 1];
                    n++;
                }
            }
            else {
                int n = 0;
                int pos = input[1] - 1;
                switches[pos] = !switches[pos];
                while(true) {
                    int l = pos - n - 1;
                    int r = pos + n + 1;
                    if(l < 0 || r >= N) break;
                    if(switches[l] != switches[r]) break;

                    switches[l] = !switches[l];
                    switches[r] = !switches[r];
                    n++;
                }
            }
        }

        System.out.print(switches[0] ? "1" : "0");
        for(int i = 1; i < N; i++) {
            if (i % 20 == 0) {
                System.out.println();
                System.out.print(switches[i] ? "1" : "0");
            } else
                System.out.print(" " + (switches[i] ? "1" : "0"));
        }
    }
}