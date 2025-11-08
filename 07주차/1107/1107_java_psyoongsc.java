import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;

    // 0 1 2 3 4 5 6 7 8 9
    static boolean[] brokenButtons = new boolean[10];

    static int closestChannel = 100;
    static int clickedCount = 0;

    static int onlyPlusMinusCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = input[0];

        if(M != 0) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int brokenButton : input) {
                brokenButtons[brokenButton] = true;
            }
        }

        onlyPlusMinusCount = Math.abs(N - 100);

        for (int i = 0; i < 10; i++) {
            if(!brokenButtons[i]) findClosestChannel(i, 1);
        }

        clickedCount += Math.abs(N - closestChannel);

        System.out.println(Math.min(onlyPlusMinusCount, clickedCount));
    }

    static void findClosestChannel(int curChannel, int depth) {
        if(Math.abs(N - closestChannel) + clickedCount > Math.abs(N - curChannel) + depth) {
            closestChannel = curChannel;
            clickedCount = depth;
        }

        if (curChannel == 0) return;

        if (curChannel > N) return;

        for (int i = 0; i < 10; i++) {
            if(!brokenButtons[i]) findClosestChannel(curChannel*10 + i, depth + 1);
        }
    }
}