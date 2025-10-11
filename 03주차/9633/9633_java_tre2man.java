import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int answer;
    static int[] location;

    static void bfs(int depth) {
        if (depth == n) {
            answer++;
            return;
        }

        for (int j = 0; j < n; j++) {
            if (isValid(depth, j)) {
                location[depth] = j;
                bfs(depth + 1);
            }
        }
    }

    private static boolean isValid(int depth, int j) {
        for (int k = 0; k < depth; k++) {
            if (location[k] == j ||
                    location[k] + (depth - k) == j ||
                    (location[k] - (depth - k) >= 0 && location[k] - (depth - k) == j)) {
                return false;
            }
        }
        return true;
    }

    static void solution(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        answer = 0;
        location = new int[n];

        for (int i = 0; i < n; i++) {
            location[0] = i;
            bfs(1);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        solution(args);
    }
}