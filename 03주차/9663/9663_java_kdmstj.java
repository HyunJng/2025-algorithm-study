import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int answer;
    static boolean[] visitedY;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        visited = new boolean[N][N];
        visitedY = new boolean[N];
        dfs(0);

        System.out.println(answer);
    }

    private static void dfs(int depth){
        if(depth >= N){
            answer++;
            return;
        }

        for(int i = 0; i < N; i++){
            if(!isValid(depth, i)){
                continue;
            }

            visitedY[i] = true;
            visited[depth][i] = true;
            dfs(depth + 1);
            visitedY[i] = false;
            visited[depth][i] = false;
        }
    }

    static int[] dx = {-1, -1, 1, 1};
    static int[] dy = {-1, 1, 1, -1};
    private static boolean isValid(int x, int y){
        if(visitedY[y]) return false;

        for(int d = 0; d < 4; d++){
            int nX = x;
            int nY = y;

            while(true){
                nX += dx[d];
                nY += dy[d];

                if(isOutOfBound(nX, nY)) break;

                if(visited[nX][nY]) return false;
            }
        }
        return true;
    }

    private static boolean isOutOfBound(int x, int y){
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}
