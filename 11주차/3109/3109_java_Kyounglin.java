import java.io.*;
import java.util.*;

public class {
    static int r, c;
    static char[][] board;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;
    static int cnt = 0;
    static boolean canReach = false;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        for(int i = 0; i<r; i++){
            String s = br.readLine();
            for(int j = 0; j<c; j++){
                board[i][j] = s.charAt(j);
            }
        }
        visited = new boolean[r][c];
        for(int i = 0; i<r; i++){
            if(dfs(i, 0)) cnt++;
        }
        System.out.println(cnt);

    }
    static boolean dfs(int x, int y){
        if(y==c-1){
            return true;
        }
        for(int i = 0; i<3; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx && nx<r && 0<=ny && ny<c && !visited[nx][ny] && board[nx][ny]=='.'){
                visited[nx][ny] = true;
                if(dfs(nx, ny)){
                    return true;
                }
            }
        }
        return false;


    }
}
