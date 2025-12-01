import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static final int EAST = 1;
    public static final int WEST = 2;
    public static final int NORTH = 3;
    public static final int SOUTH = 4;
    public static final int[] dx = {0, 0, -1, 1,};
    public static final int[] dy = {1, -1, 0, 0};
    public static int n, m, x, y, k;
    public static int[][] arr;
    public static int[] commands;
    public static int[] dice; // 0~5: 위, 아래, 북, 남, 서, 동

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        commands = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        dice = new int[6];

        bw.write(solution());

        bw.flush();
        br.close();
        bw.close();
    }

    private static String solution() {

        StringBuilder sb = new StringBuilder();
        for (int command : commands) {
            int nx = x + dx[command - 1];
            int ny = y + dy[command - 1];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }

            rollDice(command);
            if (arr[nx][ny] == 0) {
                arr[nx][ny] = dice[1];
            } else {
                dice[1] = arr[nx][ny];
                arr[nx][ny] = 0;
            }
            x = nx;
            y = ny;

            sb.append(dice[0]).append("\n");
        }
        return sb.toString();
    }

    private static void rollDice(int command) {
        int top = dice[0];
        int bottom = dice[1];
        int north = dice[2];
        int south = dice[3];
        int west = dice[4];
        int east = dice[5];

        switch (command) {
            case EAST:
                dice[0] = west;
                dice[1] = east;
                dice[4] = bottom;
                dice[5] = top;
                break;
            case WEST:
                dice[0] = east;
                dice[1] = west;
                dice[4] = top;
                dice[5] = bottom;
                break;
            case NORTH:
                dice[0] = south;
                dice[1] = north;
                dice[2] = top;
                dice[3] = bottom;
                break;
            case SOUTH:
                dice[0] = north;
                dice[1] = south;
                dice[2] = bottom;
                dice[3] = top;
                break;
        }
    }
}
