import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class Node {
        int x;
        int y;
        int depth;

        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    public static final int EMPTY = 0;
    public static final int SHARK = 9;
    public static final int[] dx = {-1, 0, 0, 1};
    public static final int[] dy = {0, -1, 1, 0};
    public static int n;
    public static int[][] arr;
    public static int sharkX, sharkY, sharkSize = 2;
    public static int eatenFishCount = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == SHARK) {
                    sharkX = i;
                    sharkY = j;
                    arr[i][j] = EMPTY;
                }
            }
        }


        bw.write(String.valueOf(solution()));

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution() {

        int answer = 0;
        while (true) {
            List<Node> candidates = findCandidates();

            if (candidates.isEmpty()) {
                break;
            }

            Node fish = eatFish(candidates);
            answer += fish.depth;
        }
        return answer;
    }

    private static List<Node> findCandidates() {

        List<Node> candidates = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        queue.offer(new Node(sharkX, sharkY, 0));
        visited[sharkX][sharkY] = true;

        int minDist = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (minDist < node.depth) {
                break;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (arr[nx][ny] > sharkSize) {
                    continue;
                }

                visited[nx][ny] = true;

                if (arr[nx][ny] != EMPTY && arr[nx][ny] < sharkSize) {
                    candidates.add(new Node(nx, ny, node.depth + 1));
                    minDist = node.depth + 1;
                }
                queue.add(new Node(nx, ny, node.depth + 1));
            }
        }

        return candidates;
    }

    private static Node eatFish(List<Node> fishes) {

        fishes.sort((o1, o2) -> {
            if (o1.depth == o2.depth) {
                if (o1.x == o2.x) {
                    return Integer.compare(o1.y, o2.y);
                }
                return Integer.compare(o1.x, o2.x);
            }
            return Integer.compare(o1.depth, o2.depth);
        });

        Node fish = fishes.get(0);
        sharkX = fish.x;
        sharkY = fish.y;
        arr[fish.x][fish.y] = EMPTY;
        eatenFishCount++;
        if (sharkSize == eatenFishCount) {
            sharkSize++;
            eatenFishCount = 0;
        }
        return fish;
    }
}