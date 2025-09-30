import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static boolean[][] c;
    static boolean[] visited;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); 
        int m = sc.nextInt(); 
        
        c = new boolean[n + 1][n + 1];
        
        for(int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            c[x][y] = true;
            c[y][x] = true; 
        }
        
        visited = new boolean[n + 1];
        int count = bfs(1);
        
        System.out.println(count);
    }
    
    private static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        int count = 0;
        
        while(!q.isEmpty()) {
            int current = q.poll();
            
            for(int next = 1; next < c.length; next++) {
                if(c[current][next] && !visited[next]) {
                    visited[next] = true;
                    q.add(next);
                    count++; 
                }
            }
        }
        
        return count;
    }
}