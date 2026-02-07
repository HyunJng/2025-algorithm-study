package 18주차.4485;
import java.io.*;
import java.util.*;

class Jelda implements Comparable<Jelda>{
    int x;
    int y;
    int rupee;
    public Jelda(int x, int y, int rupee){
        this.x = x;
        this.y = y;
        this.rupee = rupee;
    }
    @Override
    public int compareTo(Jelda ob){
        return this.rupee-ob.rupee;
    }
}

public class 4485_java_Kyounglin {
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        while(true){
            cnt++;
            int n = Integer.parseInt(br.readLine());
            if(n==0) break;

            int[][] board = new int[n][n];
            StringTokenizer st;
            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println("Problem "+cnt+": "+bfs(n, board));
        }
        
        

    }
    static int bfs(int n, int[][] board){
        PriorityQueue<Jelda> q = new PriorityQueue<>();
        q.add(new Jelda(0,0,0));

        int[][] arr = new int[n][n];
        for(int i = 0; i<n; i++){
            Arrays.fill(arr[i], Integer.MAX_VALUE);
        }
        arr[0][0] = board[0][0];

        while(!q.isEmpty()){
            Jelda cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int rupee = cur.rupee;

            if(x==n-1 && y==n-1){
                return rupee;
            }

            for(int i = 0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(0<=nx && nx<n && 0<=ny && ny<n && arr[nx][ny]>arr[x][y]+board[nx][ny]){
                    arr[nx][ny] = arr[x][y]+board[nx][ny];
                    q.add(new Jelda(nx, ny, arr[nx][ny]));

                }
;           }
        }
        return 0;
    }

}

