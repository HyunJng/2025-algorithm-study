import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String bumb = br.readLine();

        List<Character> stack = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            stack.add(current);
            char stackEnd = stack.get(stack.size() - 1);
            char bumbEnd = bumb.charAt(bumb.length() - 1);

            if (stackEnd == bumbEnd && stack.size() >= bumb.length()) {
                List<Character> getBumbFromStack = new ArrayList<>(
                        stack.subList(stack.size() - bumb.length(), stack.size()));
                String str = getBumbFromStack.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining());

                if (str.equals(bumb)) {
                    stack.subList(stack.size() - bumb.length(), stack.size()).clear();
                }
            }
        }

        if (stack.size() == 0) {
            System.out.println("FRULA");
        }
        System.out.println(stack.stream().map(String::valueOf)
                .collect(Collectors.joining()));

    }
}
