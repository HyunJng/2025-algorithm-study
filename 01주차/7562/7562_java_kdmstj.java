import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 0; test_case < T; test_case++){
            N = Integer.parseInt(br.readLine());
            String[] inputs = br.readLine().split(" ");
            int knightX = Integer.parseInt(inputs[0]);
            int knightY = Integer.parseInt(inputs[1]);

            inputs = br.readLine().split(" ");
            int destX = Integer.parseInt(inputs[0]);
            int destY = Integer.parseInt(inputs[1]);

            Queue<Node> queue = new LinkedList<>();
            boolean[][] visited = new boolean[N][N];
            visited[knightX][knightY] = true;
            queue.add(new Node(knightX, knightY, 0));

            while(!queue.isEmpty()){
                Node node = queue.poll();
                if(node.x == destX && node.y == destY){
                    System.out.println(node.move_count);
                    break;
                }

                for(int d = 0; d < 8; d++){
                    int nX = node.x + dx[d];
                    int nY = node.y + dy[d];

                    if(!isOutOfBound(nX, nY) && !visited[nX][nY]){
                        queue.add(new Node(nX, nY, node.move_count + 1));
                        visited[nX][nY] = true;
                    }

                }
            }
        }
    }

    private static boolean isOutOfBound(int x, int y){
        return x < 0 || y < 0 || x >= N || y >= N;
    }

    private static class Node{
        int x; int y; int move_count;

        Node(int x, int y, int move_count){
            this.x = x;
            this.y = y;
            this.move_count = move_count;
        }
    }
}
