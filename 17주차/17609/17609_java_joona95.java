import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    private static final int PALINDROME = 0;
    private static final int SIMILAR_PALINDROME = 1;
    private static final int NONE_PALINDROME = 2;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String str = br.readLine();
            int answer = solution(str);
            sb.append(answer).append("\n");
        }

        bw.write(sb.toString());

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution(String str) {

        int lt = 0, rt = str.length() - 1;
        while (lt < rt) {
            if (str.charAt(lt) == str.charAt(rt)) {
                lt++;
                rt--;
                continue;
            }
            if (isPalindrome(str, lt + 1, rt) || isPalindrome(str, lt, rt - 1)) {
                return SIMILAR_PALINDROME;
            }
            return NONE_PALINDROME;
        }
        return PALINDROME;
    }

    private static boolean isPalindrome(String str, int lt, int rt) {
        while (lt < rt) {
            if (str.charAt(lt) != str.charAt(rt)) {
                return false;
            }
            lt++;
            rt--;
        }
        return true;
    }

}