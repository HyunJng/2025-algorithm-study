
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] BROKEN = new boolean[10];
    static int CHANNEL_NUM;
    static int BROKEN_NUM;
    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        CHANNEL_NUM = Integer.parseInt(bufferedReader.readLine());
        BROKEN_NUM = Integer.parseInt(bufferedReader.readLine());

        if (BROKEN_NUM > 0) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < BROKEN_NUM; i++) {
                BROKEN[Integer.parseInt(st.nextToken())] = true;
            }
        }

        answer = Math.abs(CHANNEL_NUM - 100);

        System.out.println(solution());

    }


    private static int solution() {
        for (int channel = 0; channel < 1_000_000; channel++) {
            int len = canPress(channel);

            if (len > 0){
                int press = len + Math.abs(CHANNEL_NUM-channel);
                answer = Math.min(answer,press);
            }
        }
        return answer;
    }


    static int canPress(int channel){
        if(channel == 0){
            return BROKEN[0] ? 0 : 1;
        }

        int len = 0;
        while (channel > 0){
            if(BROKEN[channel%10]){
                return 0;
            }
            len++;
            channel /= 10;
        }
        return len;
    }
}
