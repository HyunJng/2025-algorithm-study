import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static List<Node> emptySpaceList = new ArrayList<>();
    static List<Node> virusList = new ArrayList<>();
    static int answer = Integer.MIN_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    emptySpaceList.add(new Node(i, j));
                }

                if (map[i][j] == 2) {
                    virusList.add(new Node(i, j));
                }
            }
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int idx, int count) {
        if (count == 3) {
            bfs();
            return;
        }

        for (int i = idx; i < emptySpaceList.size(); i++) {
            Node node = emptySpaceList.get(i);
            map[node.x][node.y] = 1;
            dfs(i + 1, count + 1);
            map[node.x][node.y] = 0;
        }
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>(virusList);
        int emptyCount = emptySpaceList.size() - 3;
        boolean[][] infected = new boolean[N][M];
        for (Node value : virusList) {
            infected[value.x][value.y] = true;
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tempX = node.x + dx[i];
                int tempY = node.y + dy[i];


                if (tempX < 0 || tempY < 0 || tempX >= N || tempY >= M) continue;
                if (map[tempX][tempY] == 1 || infected[tempX][tempY]) continue;

                queue.add(new Node(tempX, tempY));
                infected[tempX][tempY] = true;
                emptyCount--;
            }
        }

        answer = Math.max(answer, emptyCount);
    }


    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

