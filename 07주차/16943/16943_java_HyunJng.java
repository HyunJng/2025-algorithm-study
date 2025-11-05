import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    static int b, result = -1;
    static String a;
    static Set<Integer> visited = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = st.nextToken();
        b = Integer.parseInt(st.nextToken());
        solution("");
        System.out.println(result);
    }

    public static void solution(String number) {
        if(number.length() > 0 && number.charAt(0) == '0') return;
        if (number.length() ==  a.length()) {
            int temp = Integer.parseInt(number);
            if(b > temp) result = Math.max(result, temp);
            return;
        }

        for (int i = 0; i < a.length(); i++) {
            if(!visited.add(i)) continue;
            char c = a.charAt(i);
            solution(number + c);
            visited.remove(i);
        }
    }
}