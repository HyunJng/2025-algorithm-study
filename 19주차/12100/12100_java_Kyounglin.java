package 12100;

import java.io.*;
import java.util.*;

public class 12100_java_Kyounglin {
    static int n;
    static int[][] board;
    static boolean[][] summed;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        board = new int[n][n];
        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(new ArrayList<>());
        System.out.println(max);
        
    }

    static void dfs(List<Integer> selected){
        if(selected.size()==5){
            int[][] graph = new int[n][];
            for(int i = 0; i<n; i++){
                graph[i] = board[i].clone();
            }

            for(int dir: selected){
                if(dir==0) moveUp(graph);
                else if(dir==1) moveLeft(graph);
                else if(dir==2) moveDown(graph);
                else moveRight(graph);
            }
            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    max = Math.max(max, graph[i][j]);
                }
            }
            return;
        }
        for(int i = 0; i<4; i++){
            selected.add(i);
            dfs(selected);
            selected.remove(selected.size()-1);
        }
    }
    static void moveUp(int[][] graph){
        summed = new boolean[n][n];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(graph[i][j]!=0) move(i, j, 0, graph);
            }
        }
    }
    static void moveLeft(int[][] graph){
        summed = new boolean[n][n];
        for(int j = 0; j<n; j++){
            for(int i = 0; i<n; i++){
                if(graph[i][j]!=0)  move(i, j, 1, graph);
            }
        }
    }
    static void moveDown(int[][] graph){
        summed = new boolean[n][n];
        for(int i = n-1; i>=0; i--){
            for(int j = 0; j<n; j++){
                if(graph[i][j]!=0) move(i, j, 2, graph);
            }
        }
    }
    static void moveRight(int[][] graph){
        summed = new boolean[n][n];
        for(int j = n-1; j>=0; j--){
            for(int i = 0; i<n; i++){
                if(graph[i][j]!=0) move(i, j, 3, graph);
            }
        }
    }
    static void move(int sx, int sy, int dir, int[][] graph){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            if(summed[x][y]) break;
            int nx = x+dx[dir];
            int ny = y+dy[dir];
            if(0<=nx && nx<n & 0<=ny && ny<n){
                if(graph[nx][ny]==0){
                    q.add(new int[]{nx, ny});
                    graph[nx][ny] = graph[x][y];
                    graph[x][y] = 0;
                }
                else if(graph[x][y]==graph[nx][ny] && !summed[nx][ny]){
                    graph[nx][ny] = graph[x][y]*2;
                    graph[x][y] = 0;
                    summed[nx][ny] = true;
                    break;
                }
            }
        }      
    }

}