import java.util.*;
import java.io.*;

public class Main {
    static long a, b, result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        solution(0);
        bw.append(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static void solution(long num) {
        if(num > b) return;
        if(num >= a) result++;

        solution(num * 10 + 4);
        solution(num * 10 + 7);
    }
}