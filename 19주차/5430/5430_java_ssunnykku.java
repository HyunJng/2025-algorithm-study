import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        for (int i = 0; i < num; i++) {
            String func = br.readLine();
            int count = Integer.parseInt(br.readLine());
            StringBuilder str = new StringBuilder(br.readLine());
            str.deleteCharAt(str.length() - 1);
            str.deleteCharAt(0);

            Deque<String> list;
            if (str.length() == 0) {
                list = new ArrayDeque<>();
            } else {
                list = new ArrayDeque<>(Arrays.asList(str.toString().split(",")));
            }

            Deque<Character> stack = new ArrayDeque<Character>();
            for (int j = 0; j < func.length(); j++) {
                stack.addLast(func.charAt(j));
            }

            ac(stack, list);
        }

    }

    static void ac(Deque<Character> func, Deque<String> list) {
        String result = "success";
        boolean isReverse = false;

        while (func.size() != 0) {
            if (func.peekFirst() == 'R') {
                isReverse = !isReverse;
                func.pollFirst();
            } else if (func.peekFirst() == 'D') {
                if (list.size() == 0) {
                    result = "error";
                    break;
                }
                if (isReverse) {
                    list.pollLast();
                } else {
                    list.pollFirst();
                }
                func.pollFirst();
            }
        }
        if (result == "error") {
            System.out.println(result);
        } else {
            System.out.print("[");
            while (list.size() != 0) {
                if (isReverse) {
                    System.out.print(list.pollLast());
                } else {
                    System.out.print(list.pollFirst());

                }
                if (list.size() > 0)
                    System.out.print(",");

            }
            System.out.println("]");
        }
    }
}
