import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static final int OUTPUT_COUNT = 20;
    private static int switchCount;
    private static boolean[] switches;

    public void solution(int gender, int n) {
        if (isBoy(gender)) {
            toggleMultipleSwitches(n);
        } else {
            toggleAdjacentSwitches(n);
        }
    }

    private void toggleMultipleSwitches(int n) {
        for (int i = 0; i < switchCount; i++) {
            if ((i + 1) % n == 0) {
                switches[i] = !switches[i];
            }
        }
    }

    private void toggleAdjacentSwitches(int n) {
        int idx = n - 1;
        switches[idx] = !switches[idx];
        for (int i = 1; i <= Math.min(idx, switchCount - 1 - idx); i++) {
            if (switches[idx - i] != switches[idx + i]) {
                break;
            }
            switches[idx - i] = !switches[idx - i];
            switches[idx + i] = !switches[idx + i];
        }
    }

    private boolean isBoy(int n) {
        return n == 1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main main = new Main();

        switchCount = Integer.parseInt(br.readLine());
        switches = new boolean[switchCount];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < switchCount; i++) {
            switches[i] = Integer.parseInt(st.nextToken()) == 1;
        }

        int studentCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < studentCount; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            main.solution(gender, n);
        }

        for (int i = 0; i < switchCount; i++) {
            bw.write((switches[i] ? 1 : 0) + " ");
            if ((i + 1) % OUTPUT_COUNT == 0) {
                bw.write("\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}