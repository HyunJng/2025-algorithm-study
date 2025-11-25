import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] initMap;
    static final char WALL = '#', EMPTY = '.', HALL = 'O', RED = 'R', BLUE = 'B';
    static int[][] moving = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[] RED_INIT, BLUE_INIT;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        initMap = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = str.charAt(j);
                initMap[i][j] = c;
                if(c == RED) RED_INIT = new int[]{j, i};
                if(c == BLUE) BLUE_INIT = new int[]{j, i};
            }
        }
        System.out.println(solution());
    }

    public static int solution() {
        Queue<Vector> queue = new LinkedList<>();
        for (int[] move : moving) {
            queue.add(new Vector(move[0], move[1], 1,null));
        }

        while (!queue.isEmpty()) {
            Vector target = queue.poll();
            char[][] testMap = new char[N][M];
            int testCase = checkingTestCase(target, testMap);
            if(testCase > 0) return testCase;
            else if(testCase != 0) continue;

            for (int[] move : moving) {
                if((target.x == move[0] && target.y == move[1]) ||
                        (target.x == move[0] * -1 && target.y == move[1] * -1) ||
                        target.level == 10
                ) continue;
                queue.add(new Vector(move[0], move[1], target.level + 1, target));
            }
        }
        return -1;
    }

    public static int checkingTestCase(Vector target, char[][] testMap) {
        int level = target.level;
        for (int i = 0; i < N; i++) {
            testMap[i] = initMap[i].clone();
        }
        Stack<Vector> stack = new Stack<>();
        while (target != null) {
            stack.add(target);
            target = target.before;
        }

        int[] redXY = RED_INIT;
        int[] blueXY = BLUE_INIT;
        while (!stack.isEmpty()) {
            Vector v = stack.pop();
            boolean redFirst;

            if (v.x != 0) {
                if (v.x > 0) {
                    redFirst = redXY[0] > blueXY[0];
                } else {
                    redFirst = redXY[0] < blueXY[0];
                }
            } else {
                if (v.y > 0) {
                    redFirst = redXY[1] > blueXY[1];
                } else {
                    redFirst = redXY[1] < blueXY[1];
                }
            }
            if (redFirst) {
                redXY = leanMap(redXY, v, testMap);
                blueXY = leanMap(blueXY, v, testMap);
            } else {
                blueXY = leanMap(blueXY, v, testMap);
                redXY = leanMap(redXY, v, testMap);
            }
        }
        if(initMap[redXY[1]][redXY[0]] == HALL && initMap[blueXY[1]][blueXY[0]] != HALL) return level;
        if(initMap[blueXY[1]][blueXY[0]] == HALL ||
                (RED_INIT[1] == redXY[1] && RED_INIT[0] == redXY[0]
                        && BLUE_INIT[1] == blueXY[1] && BLUE_INIT[0] == blueXY[0])) return -1;
        return 0;
    }
    public static int[] leanMap(int[] startPoint, Vector target, char[][] map) {
        int tx = startPoint[0], ty = startPoint[1];
        while(isAvailable(tx + target.x, ty + target.y, map)) {
            tx += target.x;
            ty += target.y;
            if(map[ty][tx] == HALL) {
                map[startPoint[1]][startPoint[0]] = EMPTY;
                return new int[]{tx, ty};
            }
        }
        if (ty != startPoint[1] || tx != startPoint[0]) {
            map[ty][tx] = map[startPoint[1]][startPoint[0]];
            map[startPoint[1]][startPoint[0]] = EMPTY;
        }
        return new int[]{tx, ty};
    }

    public static boolean isAvailable(int x, int y, char[][] map) {
        return 0 <= x && x < M && 0 <= y && y < N && (map[y][x] == EMPTY || map[y][x] == HALL);
    }

    static class Vector {
        int x, y;
        int level;
        Vector before;

        Vector(int x, int y, int level, Vector before) {
            this.x = x; this.y = y;
            this.level = level;
            this.before = before;
        }
    }
}