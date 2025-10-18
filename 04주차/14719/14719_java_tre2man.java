import java.io.*;
import java.util.*;

public class Main {
    static public void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] raw1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int h = raw1[0];
        int w = raw1[1];
        int answer = 0;
        // 1-index
        boolean[][] map = new boolean[h + 1][w + 1];
        int[] raw2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 벽 만들기
        for (int i = 1; i <= w; i++) {
            int nowH = raw2[i - 1];
            for (int j = 1; j <= nowH; j++) {
                map[j][i] = true;
            }
        }

        boolean isBlockStarted = false;
        int blockStartedIndex = 1;
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                // 이전이 막혔고, 현재가 뚫린 경우는 물 찰수 있음
                if (j >= 2 && map[i][j - 1] == true && map[i][j] == false) {
                    blockStartedIndex = j;
                    isBlockStarted = true;
                }
                // 이전이 뚫렸고, 현재가 막힌 경우에는 물 담을수 있는지 확인 필요
                if (j >= 2 && map[i][j - 1] == false && map[i][j] == true) {
                    if (isBlockStarted == true) {
                        answer += j - blockStartedIndex;
                        isBlockStarted = false;
                    }
                }
            }
            isBlockStarted = false;
            blockStartedIndex = 1;
        }
        System.out.println(answer);
    }
}