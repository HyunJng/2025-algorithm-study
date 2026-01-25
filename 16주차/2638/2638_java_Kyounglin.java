import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] board;
    
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int time = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        board[0][0] = -1; //바깥

        while(true){
            int melt = bfs(0, 0);
            if(melt==0)break;
            else time++;
        }
        System.out.println(time);

        
    }
    static int bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        boolean[][] visited = new boolean[n][m];
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            for(int i = 0; i<4; i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];
                if(0<=nx && nx<n && 0<=ny && ny<m){
                    if(board[nx][ny]!=1 && !visited[nx][ny]){ 
                        visited[nx][ny] = true;
                        System.out.println(nx+" "+ny);
                        board[nx][ny] = -1; //바깥;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
        int meltCnt=0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(board[i][j]==1 && isTwoMoreAir(i, j)){
                    board[i][j] = 0;
                    meltCnt++;
                }
            }
        }
        System.out.println("--");
        return meltCnt;
    }
    static boolean isTwoMoreAir(int x, int y){
        int air = 0;
        
        for(int i = 0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx && nx<n && 0<=ny && ny<m && board[nx][ny]==-1){
                air++;
            }
        }
        if(air>=2) return true;
        else return false;
    }

}
