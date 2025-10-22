import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans1244 {

    static int N;
    static int[] switchArr;
    static int M;
    static final int MAN = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        switchArr = new int[N + 1]; //1-based
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            switchArr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (gender == MAN) {
                simulationByMan(num);
            } else {
                simulationByWoman(num);
            }
        }

        output();
    }

    private static void output() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(switchArr[i]).append(" ");
            if(i % 20 == 0) sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void simulationByMan(int num) {
        int multiplyNum = 1;
        while (true) {
            int tmpNum = num * multiplyNum++;

            if (tmpNum > N) {
                break;
            }

            updateSwitch(tmpNum);
        }
    }

    private static void simulationByWoman(int num) {
        updateSwitch(num);

        int leftIdx = num - 1;
        int rightIdx = num + 1;
        while (true) {
            if (isOutOfBound(leftIdx, rightIdx)) break;

            if (switchArr[leftIdx] != switchArr[rightIdx]) break;

            updateSwitch(leftIdx--);
            updateSwitch(rightIdx++);
        }
    }

    private static void updateSwitch(int idx) {
        switchArr[idx] = (switchArr[idx] + 1) % 2;
    }

    private static boolean isOutOfBound(int left, int right) {
        return left < 1 || right > N;
    }
}
