import java.io.*;
import java.util.*;

public class 15686_java_Kyounglin{
    static int n;
    static int m;
    static int[][] board;
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        visited  = new boolean[n][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]==1) houses.add(new int[]{i,j});
                else if(board[i][j]==2) chickens.add(new int[]{i,j}); 
            }
        }

        dfs(0, new ArrayList<>());
        System.out.println(min);

    }
    static void dfs(int start, List<int[]> selected){
        if(selected.size()==m){
            int sum = 0;
            for(int[] house: houses){
                sum+= chickenDist(house[0], house[1], selected);
            }
            min = Math.min(min, sum);
        }
        for(int i = start; i<chickens.size(); i++){
            int[] chicken = chickens.get(i);
            if(!visited[chicken[0]][chicken[1]]){
                visited[chicken[0]][chicken[1]] = true;
                selected.add(new int[]{chicken[0], chicken[1]});
                dfs(i+1, selected);
                selected.remove(selected.size()-1);
                visited[chicken[0]][chicken[1]] = false;
            }
        }
    }
    static int chickenDist(int x, int y, List<int[]> selected){
        int sum = Integer.MAX_VALUE;
        for(int[] arr: selected){
            sum = Math.min(Math.abs(x-arr[0])+Math.abs(y-arr[1]), sum);
        }
        return sum;
    }
}

