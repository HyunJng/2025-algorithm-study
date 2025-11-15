import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R;
    static int C;
    static int T;
    static int[][] map;
    static int airCleanerTop;
    static int airCleanerBottom;

    // 4방향: 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == -1){
                    if (airCleanerTop == 0) {
                        airCleanerTop = i;
                    } else {
                        airCleanerBottom = i;
                    }
                }
            }
        }

        for (int t = 0; t < T; t++) {
            spread();
            operateCleaner();
        }

        System.out.println(getTotalDust());
    }

    static void spread(){
        int[][] temp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp[i][j] = map[i][j];
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 0) {  // 미세먼지가 있는 칸만
                    int amount = map[r][c] / 5;
                    int count = 0;

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != -1) {
                            temp[nr][nc] += amount;
                            count++;
                        }
                    }

                    temp[r][c] -= amount * count;
                }
            }
        }

        map = temp;
    }

    static void operateCleaner(){
        counterClockwise(airCleanerTop);
        clockwise(airCleanerBottom);
    }

    static void counterClockwise(int row) {
        for (int r = row - 1; r > 0; r--) {
            map[r][0] = map[r - 1][0];
        }

        for (int c = 0; c < C - 1; c++) {
            map[0][c] = map[0][c + 1];
        }

        for (int r = 0; r < row; r++) {
            map[r][C - 1] = map[r + 1][C - 1];
        }

        for (int c = C - 1; c > 1; c--) {
            map[row][c] = map[row][c - 1];
        }

        map[row][1] = 0;
    }

    static void clockwise(int row) {
        for (int r = row + 1; r < R - 1; r++) {
            map[r][0] = map[r + 1][0];
        }

        for (int c = 0; c < C - 1; c++) {
            map[R - 1][c] = map[R - 1][c + 1];
        }

        for (int r = R - 1; r > row; r--) {
            map[r][C - 1] = map[r - 1][C - 1];
        }

        for (int c = C - 1; c > 1; c--) {
            map[row][c] = map[row][c - 1];
        }

        map[row][1] = 0;
    }

    static int getTotalDust(){
        int total = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] > 0){
                    total += map[i][j];
                }
            }
        }
        return total;
    }
}