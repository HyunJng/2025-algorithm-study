import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static ArrayList<CCTV> cctvList = new ArrayList<>();
    static int minBlindSpot = Integer.MAX_VALUE;

    // 방향: 상(0), 우(1), 하(2), 좌(3)
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};

    static int [][][] directions = {
            {},  // 0번
            {{0}, {1}, {2}, {3}},  // 1번: 4방향
            {{0, 2}, {1, 3}},  // 2번: 2가지
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},  // 3번: 4가지
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},  // 4번: 4가지
            {{0, 1, 2, 3}}  // 5번: 1가지
    };

    static class CCTV{
        int x;
        int y;
        int type;

        CCTV(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5){
                    cctvList.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        dfs(0,map);
        System.out.println(minBlindSpot);
    }

    static void dfs(int depth, int[][] currentMap){
        if(depth == cctvList.size()){
            int blindSpot = countBlindSpot(currentMap);
            minBlindSpot = Math.min(minBlindSpot,blindSpot);
            return;
        }

        CCTV cctv = cctvList.get(depth);
        int type = cctv.type;

        for (int[] directionSet : directions[type]) {
            int[][] newMap = copyMap(currentMap);

            for(int dir : directionSet){
                watch(newMap, cctv.x, cctv.y , dir);
            }

            dfs(depth+1, newMap);
        }
    }

    static void watch(int[][] map, int x, int y, int direction){
        int nx = x + dx[direction];
        int ny = y + dy[direction];

        while(isInRange(nx,ny)){
            if(map[nx][ny] == 6){
                break;
            }

            if (map[nx][ny] == 0){
                map[nx][ny] = 7; //감시표시
            }
            nx += dx[direction];
            ny += dy[direction];
        }
    }

    static boolean isInRange(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static int countBlindSpot(int[][] map){
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
    }

    static int[][] copyMap(int[][] map){
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }
}
