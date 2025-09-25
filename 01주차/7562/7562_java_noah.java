import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int L = sc.nextInt();
            int startX = sc.nextInt();
            int startY = sc.nextInt();
            int targetX = sc.nextInt();
            int targetY = sc.nextInt();

            int result = findMinMove(L, startX, startY, targetX, targetY);
            System.out.println(result);
        }

        sc.close();
    }

    private static int findMinMove(int L, int startX, int startY, int targetX, int targetY) {
        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
        
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[L][L];

        q.add(new Node(startX, startY, 0));
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.x == targetX && node.y == targetY) {
                return node.move;
            }

            for (int i = 0; i < 8; i++) {
                int x = node.x + dx[i];
                int y = node.y + dy[i];

                if (x >= 0 && x < L && y >= 0 && y < L && !visited[x][y]) {
                    visited[x][y] = true;
                    q.add(new Node(x, y, node.move + 1));
                }
            }
        }

        return 0;
    }

    private static class Node {
        final int x, y, move;

        Node(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}