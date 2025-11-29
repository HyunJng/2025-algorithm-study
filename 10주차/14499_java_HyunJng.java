import java.io.*;
import java.util.*;

public class Main {
    static int N, M, MOVE;
    static final int EAST = 1, WEST = 2, NORTH = 3, SOUTH = 4;
    static final int DICE_NUM = 4, BOTTOM_IDX = 2, TOP_IDX = 0;
    static int[] now, diceRow, diceCol;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        now = new int[]{x, y};
        MOVE = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        diceRow = new int[DICE_NUM];
        diceCol = new int[DICE_NUM];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < MOVE; i++) {
            int result = solution(Integer.parseInt(st.nextToken()));
            if (result >= 0) {
                bw.append(String.valueOf(result));
                bw.append("\n");
            }
        }

        bw.flush();
        bw.close();
    }

    public static int solution(int moveCase) {
        if (!moveMap(moveCase)) return -1;
        if (map[now[1]][now[0]] == 0) {
            map[now[1]][now[0]] = diceRow[BOTTOM_IDX];
        } else {
            diceRow[BOTTOM_IDX] = map[now[1]][now[0]];
            diceCol[BOTTOM_IDX] = map[now[1]][now[0]];
            map[now[1]][now[0]] = 0;
        }
        return diceRow[TOP_IDX];
    }

    public static boolean moveMap(int moveCase) {
        int x = now[0], y = now[1];
        int[] tempRow = diceRow.clone(), tempCol = diceCol.clone();
        switch(moveCase) {
            case EAST : {
                x += 1;
                tempRow = moveDice(diceRow, true);
                tempCol[BOTTOM_IDX] = tempRow[BOTTOM_IDX];
                tempCol[TOP_IDX] = tempRow[TOP_IDX];
            } break;
            case WEST : {
                x -= 1;
                tempRow = moveDice(diceRow, false);
                tempCol[BOTTOM_IDX] = tempRow[BOTTOM_IDX];
                tempCol[TOP_IDX] = tempRow[TOP_IDX];
            } break;
            case NORTH : {
                y -= 1;
                tempCol = moveDice(diceCol, false);
                tempRow[BOTTOM_IDX] = tempCol[BOTTOM_IDX];
                tempRow[TOP_IDX] = tempCol[TOP_IDX];
            } break;
            case SOUTH : {
                y += 1;
                tempCol = moveDice(diceCol, true);
                tempRow[BOTTOM_IDX] = tempCol[BOTTOM_IDX];
                tempRow[TOP_IDX] = tempCol[TOP_IDX];
            } break;
        }

        boolean result = isRange(x, y);
        if(result){
            now[0] = x; now[1] = y;
            diceCol = tempCol;
            diceRow = tempRow;
        }
        return result;
    }

    public static int[] moveDice(int[] target, boolean isRight) {
        int[] temp = new int[DICE_NUM];
        int plus = (isRight) ? 1 : -1;

        for(int i = 0; i < DICE_NUM; i++){
            temp[circularDiceArray(i + plus)] = target[i];
        }
        return temp;
    }

    public static int circularDiceArray(int i) {
        i %= DICE_NUM;
        return (i < 0) ? DICE_NUM + i : i;
    }

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }
}