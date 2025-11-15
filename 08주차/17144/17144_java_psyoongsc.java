import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, T;

    static int[][] room;

    static int circulator_top_y = 0, circulator_bottom_y = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = input[0];
        C = input[1];
        T = input[2];

        room = new int[R][C];
        for (int i = 0; i < R; i++) {
            room[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if(room[i][0] == -1) {
                if(circulator_top_y == 0) circulator_top_y = i;
                else circulator_bottom_y = i;
            }
        }

        for (int i = 0; i < T; i++) {
            spread_dust(room);
            air_circulate();
        }

        int answer = sum_dust(room);
        System.out.println(answer);
    }

    static void spread_dust(int[][] curRoom) {
        int[][] after_spread = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(curRoom[i][j] == -1) {
                    after_spread[i][j] = -1;
                } else if (curRoom[i][j] >= 5) {
                    int ARC = curRoom[i][j];
                    int ARC5 = ARC / 5;

                    int spread_count = 0;
                    if(i-1>=0 && curRoom[i-1][j] != -1) {
                        after_spread[i-1][j] += ARC5;
                        spread_count++;
                    }
                    if(j-1>=0 && curRoom[i][j-1] != -1) {
                        after_spread[i][j-1] += ARC5;
                        spread_count++;
                    }
                    if(i+1<R && curRoom[i+1][j] != -1) {
                        after_spread[i+1][j] += ARC5;
                        spread_count++;
                    }
                    if(j+1<C && curRoom[i][j+1] != -1) {
                        after_spread[i][j+1] += ARC5;
                        spread_count++;
                    }
                    after_spread[i][j] += ARC - ARC5 * spread_count;
                } else {
                    after_spread[i][j] += curRoom[i][j];
                }
            }
        }

        room = after_spread;
    }

    static void air_circulate() {
        // 상단
        int before_dust = 0;
        for (int i = 1; i < C; i++) {
            int curDust = room[circulator_top_y][i];
            room[circulator_top_y][i] = before_dust;
            before_dust = curDust;
        }
        for (int i = circulator_top_y-1; i >= 0; i--) {
            int curDust = room[i][C-1];
            room[i][C-1] = before_dust;
            before_dust = curDust;
        }
        for (int i = C-2; i >= 0; i--) {
            int curDust = room[0][i];
            room[0][i] = before_dust;
            before_dust = curDust;
        }
        for (int i = 1; i < circulator_top_y; i++) {
            int curDust = room[i][0];
            room[i][0] = before_dust;
            before_dust = curDust;
        }

        // 하단
        before_dust = 0;
        for (int i = 1; i < C; i++) {
            int curDust = room[circulator_bottom_y][i];
            room[circulator_bottom_y][i] = before_dust;
            before_dust = curDust;
        }
        for (int i = circulator_bottom_y+1; i < R; i++) {
            int curDust = room[i][C-1];
            room[i][C-1] = before_dust;
            before_dust = curDust;
        }
        for (int i = C-2; i >= 0; i--) {
            int curDust = room[R-1][i];
            room[R-1][i] = before_dust;
            before_dust = curDust;
        }
        for (int i = R-2; i > circulator_bottom_y; i--) {
            int curDust = room[i][0];
            room[i][0] = before_dust;
            before_dust = curDust;
        }
    }

    static int sum_dust(int[][] curRoom) {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sum += curRoom[i][j];
            }
        }

        return sum + 2;
    }

}