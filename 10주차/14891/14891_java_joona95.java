import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static final int NORTH_POLE = 0;
    public static final int SOUTH_POLE = 1;
    public static final int GEAR_COUNT = 4;
    public static final int TOOTH_COUNT = 8;
    public static final int CLOCKWISE = 1;
    public static final int COUNTER_CLOCKWISE = -1;
    public static int[] directions;
    public static int[][] arr;
    public static int k;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        arr = new int[GEAR_COUNT][TOOTH_COUNT];

        for (int i = 0; i < GEAR_COUNT; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < TOOTH_COUNT; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearIdx = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());
            rotate(gearIdx, direction);
        }

        int answer = sumGrades();
        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    private static void rotate(int gearIdx, int direction) {

        directions = new int[GEAR_COUNT];
        directions[gearIdx] = direction;

        for (int i = gearIdx - 1; i >= 0; i--) {
            if (arr[i][2] == arr[i + 1][6]) {
                break;
            }
            directions[i] = -directions[i + 1];
        }

        for (int i = gearIdx + 1; i < GEAR_COUNT; i++) {
            if (arr[i - 1][2] == arr[i][6]) {
                break;
            }
            directions[i] = -directions[i - 1];
        }

        for (int i = 0; i < GEAR_COUNT; i++) {
            if (directions[i] == CLOCKWISE) {
                rotateClockwise(i);
            } else if (directions[i] == COUNTER_CLOCKWISE) {
                rotateCounterClockwise(i);
            }
        }
    }

    private static void rotateClockwise(int gearIdx) {
        int tmp = arr[gearIdx][TOOTH_COUNT - 1];
        for (int i = TOOTH_COUNT - 1; i > 0; i--) {
            arr[gearIdx][i] = arr[gearIdx][i - 1];
        }
        arr[gearIdx][0] = tmp;
    }

    private static void rotateCounterClockwise(int gearIdx) {
        int tmp = arr[gearIdx][0];
        for (int j = 0; j < TOOTH_COUNT - 1; j++) {
            arr[gearIdx][j] = arr[gearIdx][j + 1];
        }
        arr[gearIdx][TOOTH_COUNT - 1] = tmp;
    }

    private static int sumGrades() {
        int sum = 0;
        for (int i = 0; i < GEAR_COUNT; i++) {
            if (arr[i][0] == SOUTH_POLE) {
                sum += (int) Math.pow(2, i);
            }
        }
        return sum;
    }
}