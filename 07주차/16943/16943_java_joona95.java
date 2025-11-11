import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int a, b, answer = -1;
    public static int[] nums;
    public static boolean[] checked;

    public static void dfs(int level, int num) {

        if (level == nums.length) {
            if (num < b) {
                answer = Math.max(answer, num);
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if ((level != 0 || nums[i] != 0) && !checked[i]) {
                checked[i] = true;
                dfs(level + 1, num * 10 + nums[i]);
                checked[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        String str = String.valueOf(a);
        nums = new int[str.length()];
        checked = new boolean[str.length()];
        for (int i = 0; i < str.length(); i++) {
            nums[i] = str.charAt(i) - '0';
        }

        dfs(0, 0);

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }
}