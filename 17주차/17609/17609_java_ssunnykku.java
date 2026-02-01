import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean isPalindrome(int l, int r, String str) {
        while (l < r) {
            if (str.charAt(l) == str.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }

    static int check(String str) {
        int result = 0;
        int l = 0;
        int r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l) == str.charAt(r)) {
                l++;
                r--;
            } else {
                if (isPalindrome(l + 1, r, str) || isPalindrome(l, r - 1, str)) {
                    return 1;
                } else {
                    return 2;
                }

            }
        }
        return result;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int len = Integer.parseInt(br.readLine());
        String[] strings = new String[len];

        for (int i = 0; i < len; i++) {
            strings[i] = br.readLine();
        }

        for (int i = 0; i < strings.length; i++) {
            System.out.println(check(strings[i]));
        }

    }

}
