import java.io.*;
import java.util.*;

public class Main {
    public static final int N = 4, TOOTH = 8, FACE = 2, SPIN_CLOCK = 1, SPIN_ANTI_CLOCK = -1;
    public static int[][] wheels;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        wheels = new int[N][TOOTH];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < TOOTH; j++) {
                wheels[i][j] = line.charAt(j) - '0';
            }
        }
        int testCase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCase; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            solution(temp[0], temp[1]);
        }

        System.out.println(calculateResult());
    }

    public static int calculateResult() {
        int sum = 0, score = 1;
        for (int i = 0; i < N; i++) {
            if (wheels[i][0] == 1) {
                sum += score;
            }
            score *= 2;
        }
        return sum;
    }

    public static void solution(int num, int spinCase) {
        int target = num - 1;
        List<int[]> save = new ArrayList<>();
        save.add(new int[]{target, spinCase});

        int targetSpin = spinCase;
        for (int i = target + 1; i < N; i++) {
            if (wheels[i - 1][FACE] != wheels[i][TOOTH - FACE]) {
                targetSpin *= -1;
                save.add(new int[]{i, targetSpin});
            } else break;
        }

        targetSpin = spinCase;
        for (int i = target - 1; i >= 0; i--) {
            if (wheels[i + 1][TOOTH - FACE] != wheels[i][FACE]) {
                targetSpin *= -1;
                save.add(new int[]{i, targetSpin});
            } else break;
        }

        for (int[] t : save) {
            spin(t[0], t[1]);
        }
    }

    public static void spin(int target, int spinCase) {
        int[] temp = new int[TOOTH];
        switch(spinCase) {
            case SPIN_CLOCK : {
                for (int i = 0; i < TOOTH; i++) {
                    temp[circularArray(i + 1)] = wheels[target][i];
                }
            } break;
            case SPIN_ANTI_CLOCK : {
                for (int i = 0; i < TOOTH; i++) {
                    temp[circularArray(i - 1)] = wheels[target][i];
                }
            }
        }
        wheels[target] = temp;
    }

    public static int circularArray(int i) {
        i = i % TOOTH;
        return i < 0 ? TOOTH + i : i;
    }
}