import java.io.*;
import java.util.*;

public class 17143_java_Kyounglin{
    static int R, C, M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[][][] board;
    static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[R][C][3];

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());
            board[r][c] = new int[]{s, d,z};
        }
        for(int fisher = 0; fisher<C; fisher++){
            for(int i = 0; i<R; i++){
                if(board[i][fisher][2]!=0){
                    answer+= board[i][fisher][2];
                    board[i][fisher] = new int[3];
                    break;
                }
            }
            int[][][] next = new int[R][C][3];
            for(int i = 0; i<R; i++){
                for(int j = 0; j<C; j++){
                    if(board[i][j][2]==0) continue;
                    int s = board[i][j][0];
                    int d = board[i][j][1];
                    int z = board[i][j][2];

                    int x = i;
                    int y = j;

                    for(int t = 0; t<s; t++){
                        int nx = x+dx[d];
                        int ny = y+dy[d];
                        if(0<=nx && nx <R && 0<=ny && ny<C){
                            
                        }else{
                            d = d%2==0? d+1: d-1;
                            nx = x+dx[d];
                            ny = y+dy[d];
                        }
                        x = nx;
                        y = ny;
                    }
                    if(next[x][y][2]<z){
                        next[x][y] = new int[]{s, d, z};
                    }
                }
            }
            board = next;
        }
        System.out.println(answer);
    }
   
    
}
