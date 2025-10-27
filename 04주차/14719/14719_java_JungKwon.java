package week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_14719 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] hw = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int W = hw[1];

        int[] heights = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(calculateTrappedWater(heights, W));
    }

    private static int calculateTrappedWater(int[] heights, int width) {
        int[] leftMax = new int[width];
        int[] rightMax = new int[width];

        leftMax[0] = heights[0];
        for (int i = 1; i < width; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], heights[i]);
        }

        rightMax[width - 1] = heights[width - 1];
        for (int i = width - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], heights[i]);
        }

        int totalWater = 0;
        for (int i = 0; i < width; i++) {
            int waterHeight = Math.min(leftMax[i], rightMax[i]) - heights[i];
            if (waterHeight > 0) {
                totalWater += waterHeight;
            }
        }

        return totalWater;
    }
}