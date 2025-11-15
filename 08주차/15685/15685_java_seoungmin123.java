import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static final int COUNT_SQUARE = 100;
    static boolean[][] map = new boolean[COUNT_SQUARE+1][COUNT_SQUARE+1];
    // 방향: 0→, 1↑, 2←, 3↓
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int d = Integer.parseInt(input[2]);
            int g = Integer.parseInt(input[3]);
            drawDragonCurve(x, y, d, g);
        }

        System.out.println(countSquares());
    }

    static void drawDragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        // g세대까지 방향 생성
        for (int i = 0; i < g; i++) {
            int size = directions.size();
            for (int j = size - 1; j >= 0; j--) {
                int current = directions.get(j);
                int next = (current + 1) % 4;
                directions.add(next);
            }
        }

        // 시작점 표시
        map[x][y] = true;

        // 모든 방향으로 이동하며 표시
        for (int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            map[x][y] = true;
        }
    }

    static int countSquares() {
        int count = 0;
        for (int i = 0; i < COUNT_SQUARE; i++) {
            for (int j = 0; j < COUNT_SQUARE; j++) {
                if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
                    count++;
                }
            }
        }
        return count;
    }
}