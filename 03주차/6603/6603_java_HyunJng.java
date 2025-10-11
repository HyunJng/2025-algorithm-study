import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {
    static int r = 6, n;
    static int[] testCase;
    static List<List<Integer>> result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            testCase = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            n = testCase[0];
            if(n == 0) break;

            result = new ArrayList<>();
            solution(1, 0, new ArrayList<>());

            result.forEach(
                    list -> System.out.println(
                            list.stream().map(String::valueOf).collect(Collectors.joining(" "))
                    )
            );
            System.out.println();
        }
    }

    public static void solution(int index, int depth, List<Integer> current) {
        if(depth == r) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i <= n; i++) {
            current.add(testCase[i]);
            solution(i + 1, depth + 1, current);
            current.remove(current.size() - 1);
        }
    }
}