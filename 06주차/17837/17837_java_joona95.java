import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final int LIMIT_COUNT = 1000;
    private static final int WHITE = 0, RED = 1, BLUE = 2;
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};
    private static final List<Horse> horses = new ArrayList<>();
    private static List<Integer>[][] board;
    private static int n, k;
    private static int[][] arr;

    static class Horse {
        int x;
        int y;
        int direction;

        public Horse(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public void reverse() {
            this.direction = direction % 2 == 0 ? direction - 1 : direction + 1;
        }

        public void move(int nx, int ny) {
            this.x = nx;
            this.y = ny;
        }
    }

    public static int solution() {

        int cnt = 0;
        while (cnt < LIMIT_COUNT) {
            cnt++;

            for (int i = 0; i < k; i++) {
                Horse horse = horses.get(i);
                int nx = horse.x + dx[horse.direction - 1];
                int ny = horse.y + dy[horse.direction - 1];

                if (nx <= 0 || ny <= 0 || nx > n || ny > n || arr[nx][ny] == BLUE) {
                    horse.reverse();
                    nx = horse.x + dx[horse.direction - 1];
                    ny = horse.y + dy[horse.direction - 1];
                    if (nx <= 0 || ny <= 0 || nx > n || ny > n || arr[nx][ny] == BLUE) {
                        continue;
                    }
                }

                List<Integer> moves = new ArrayList<>();
                int horseCnt = board[horse.x][horse.y].size();
                int position = horseCnt;
                for (int j = 0; j < horseCnt; j++) {
                    if (board[horse.x][horse.y].get(j) == i) {
                        position = j;
                    }
                    if (j >= position) {
                        moves.add(board[horse.x][horse.y].get(j));
                    }
                }

                board[horse.x][horse.y] = new ArrayList<>(board[horse.x][horse.y].subList(0, position));

                if (arr[nx][ny] == RED) {
                    Collections.reverse(moves);
                }

                for (Integer idx : moves) {
                    board[nx][ny].add(idx);
                    horses.get(idx).move(nx, ny);
                }

                if (board[nx][ny].size() >= 4) {
                    return cnt;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][n + 1];
        board = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            horses.add(new Horse(x, y, direction));
            board[x][y].add(horses.size() - 1);
        }

        bw.write(String.valueOf(solution()));

        bw.flush();
        br.close();
        bw.close();
    }
}