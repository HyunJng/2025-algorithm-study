import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long A;
    static long B;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        solution(0);
        System.out.println(count);
    }


    static void solution(long num){
        if (num > B) return;
        if (num >= A) count++;

        solution(num * 10 +4);
        solution(num * 10 +7);

    }

}

