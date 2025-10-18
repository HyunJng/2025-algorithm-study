import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int H, W;
    static int[][] world;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        H = input1[0];
        W = input1[1];
        world = new int[H][W];

        int[] input2 =  Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i = 0; i < W; i++) {
            for(int j = 0; j < input2[i]; j++) world[j][i] = 1;
        }

        int water = 0;
        for(int i = 0; i < H; i++) {
            boolean wall_encountered = false;
            int tmp_water = 0;
            for(int j = 0; j < W; j++) {
                if(world[i][j] == 1) {
                    if(wall_encountered) {
                        water += tmp_water;
                    }

                    wall_encountered = true;
                    tmp_water = 0;
                } else {
                    tmp_water++;
                }
            }

            if(!wall_encountered) break;
        }

        System.out.println(water);
    }
}