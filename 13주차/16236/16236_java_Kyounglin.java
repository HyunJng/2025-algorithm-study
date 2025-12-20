import java.io.*;
import java.util.*;

class Fish implements Comparable<Fish>{
    int x, y, dist;
    public Fish(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    @Override
    public int compareTo(Fish o){
        if(this.dist == o.dist){
            if(this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
        return this.dist - o.dist;
    }
}

public class 16236_java_Kyounglin {
    static int n;
    static int[][] board;
    static int ate = 0, size = 2;
    static int sx, sy, time = 0;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 9){
                    sx = i;
                    sy = j;
                    board[i][j] = 0;
                }
            }
        }

        while(true){
            Fish target = findFish();
            if(target == null) break;

            time += target.dist;
            ate++;
            if(ate == size){
                size++;
                ate = 0;
            }

            sx = target.x;
            sy = target.y;
            board[sx][sy] = 0;
        }

        System.out.println(time);
    }

    static Fish findFish(){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<Fish> pq = new PriorityQueue<>();

        q.add(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];

            if(board[x][y] != 0 && board[x][y] < size){
                pq.add(new Fish(x, y, d));
            }

            for(int i=0;i<4;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx>=0 && nx<n && ny>=0 && ny<n && !visited[nx][ny] && board[nx][ny] <= size){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, d+1});
                }
            }
        }

        return pq.isEmpty() ? null : pq.poll();
    }
}
