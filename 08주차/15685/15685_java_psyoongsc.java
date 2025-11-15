import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    static int[][] map = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];

        while (N-- > 0) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = input[0];
            int y = input[1];
            int d = input[2];
            int g = input[3];

            draw_dragon(x, y, d, g);
        }

        int answer = count_single_square();
        System.out.println(answer);
    }

    static void draw_dragon(int x, int y, int d, int g) {
        // 방향: 0(→), 1(↑), 2(←), 3(↓)
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        for (int i = 0; i < g; i++) {
            int size = directions.size();
            // 회전
            for (int j = size - 1; j >= 0; j--) {
                int newDir = (directions.get(j) + 1) % 4;
                directions.add(newDir);
            }
        }

        map[y][x] = 1;
        for (int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            map[y][x] = 1;
        }
    }

    static int count_single_square() {
        int count = 0;
        for (int i = 0; i < map.length-1; i++) {
            for (int j = 0; j < map[i].length-1; j++) {
                if (map[i][j] != 0 && map[i+1][j] != 0 && map[i][j+1] != 0 && map[i+1][j+1] != 0) count++;
            }
        }

        return count;
    }
}