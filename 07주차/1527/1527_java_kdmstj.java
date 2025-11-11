import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long min, max;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        min = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());

        dfs(0);

        System.out.println(answer);
    }

    static int[] nums = {4, 7};
    private static void dfs(long num) {

        for (int i = 0; i < nums.length; i++) {
            long newNum = num * 10 + nums[i];
            if (newNum <= max) {
                if(min <= newNum){
                    answer++;
                }
                dfs(newNum);
            }
        }
    }
}
