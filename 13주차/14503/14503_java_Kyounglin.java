import java.util.*;
import java.io.*;

public class 14503_java_Kyounglin {
        static int n;
    static int m;
    static int r;
    static int c;
    static int d;

    static int[][] board;
    static int cnt = 0;
    static int[] dx = {-1, 0, 1, 0}; //북 동 남 서
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs();
        System.out.println(cnt);
    }
    static void dfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c, d});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dir = cur[2];

            if(board[x][y]==0){
                board[x][y] = -1;
                cnt++;
            }
            boolean find = false;
            for(int i = 0; i<4; i++){
                int nx = x+dx[(dir+3-i)%4];
                int ny = y+dy[(dir+3-i)%4];
                if(0<=nx && nx<n && 0<=ny && ny<m && board[nx][ny]==0){
                    find = true;
                    q.add(new int[]{nx, ny, (dir+3-i)%4});
                    break;
                }
            }
            if(!find){
                int nx = x-dx[dir];
                int ny = y-dy[dir];
                if(0<=nx && nx<n && 0<=ny && ny<m && board[nx][ny]!=1){
                    q.add(new int[]{nx, ny, dir});
                }
            }
        }

    }
    
}
