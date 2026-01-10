import java.io.*;
import java.util.*;

public class Main {

    public static class Number implements Comparable<Number>{
        char[] num;

        Number(String s) {
            num = s.toCharArray();
        }

        public int compareTo(Number o) {
            int min = Math.min(o.num.length, this.num.length);

            for (int i = 0; i < min; i++) {
                if(num[i] == o.num[i]) continue;
                return o.num[i] - num[i];
            }

            if (this.num.length != o.num.length) {
                String thisstr = String.valueOf(this.num);
                String ostr = String.valueOf(o.num);

                char[] thisFirst = (thisstr + ostr).toCharArray();
                char[] oFirst = (ostr + thisstr).toCharArray();

                for (int i = min; i < thisstr.length() + ostr.length(); i++) {
                    if(thisFirst[i] == oFirst[i]) continue;
                    return (thisFirst[i] > oFirst[i])? -1 : 1;
                }
            }
            return 0;
        }
    }

    static int N;
    static Number[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new Number[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = new Number(st.nextToken());
        }

        System.out.println(solution());
    }

    public static String solution() {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(numbers);
        if(numbers[0].num[0] == '0') return "0";
        for (int i = 0; i < N; i++) {
            sb.append(numbers[i].num);
        }
        return sb.toString();
    }
}