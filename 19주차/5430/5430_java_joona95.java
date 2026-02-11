import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder answer = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            String arrays = br.readLine();
            String numsStr = arrays.substring(1, arrays.length() - 1).trim();
            if (!numsStr.isEmpty()) {
                String[] nums = numsStr.split(",");
                for (int j = 0; j < nums.length; j++) {
                    arr[j] = Integer.parseInt(nums[j]);
                }
            }
            answer.append(solution(p, arr)).append("\n");
        }

        bw.write(answer.toString());

        bw.flush();
        br.close();
        bw.close();
    }

    public static String solution(String p, int[] arr) {

        Deque<Integer> deque = new ArrayDeque<>();
        for (int num : arr) {
            deque.addLast(num);
        }

        boolean isReversed = false;
        String[] commands = p.split("");
        for (String command : commands) {
            if (command.equals("R")) {
                isReversed = !isReversed;
                continue;
            }
            if (deque.isEmpty()) {
                return "error";
            }
            if (isReversed) {
                deque.pollLast();
            } else {
                deque.pollFirst();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        while (!deque.isEmpty()) {
            sb.append(isReversed ? deque.pollLast() : deque.pollFirst());
            if (!deque.isEmpty()) {
                sb.append(",");
            }
        }

        return sb.append("]").toString();
    }
}