import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static void solve(int boardLength, int p1x, int p1y, int p2x, int p2y) {
        // 시작점과 도착점이 같다면 0 출력 후 반환
        if (p1x == p2x && p1y == p2y) {
            System.out.println(0);
            return;
        }

        boolean[][] visited = new boolean[boardLength][boardLength];
        Queue<int[]> q = new LinkedList<>();

        // 나이트가 이동할 수 있는 모든 경로
        int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
        int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };

        q.add(new int[] { p1x, p1y, 0 });
        visited[p1y][p1x] = true;

        while (!q.isEmpty()) {
            int[] point = q.poll();
            int depth = point[2] + 1;
            for (int i = 0; i < 8; i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];

                // 체스판 범위 벗어나면 패스
                if (nx < 0 || nx >= boardLength || ny < 0 || ny >= boardLength) {
                    continue;
                }

                // 답을 찾았다면 출력 후 반환
                if (nx == p2x && ny == p2y) {
                    System.out.println(depth);
                    return;
                }

                // 방문했다면 패스
                if (visited[ny][nx]) {
                    continue;
                }

                // 다음 노드 탐색 위해 큐에 추가
                visited[ny][nx] = true;
                q.add(new int[] { nx, ny, depth });
            }
        }
    }

    static void solution(String[] args) throws FileNotFoundException {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);

        // 데이터 파싱
        int tc = sc.nextInt();
        for (int i = 0; i < tc; i++) {
            int boardLength = sc.nextInt();
            // p1 은 나이트가 현재 있는 칸
            int p1x = sc.nextInt();
            int p1y = sc.nextInt();
            // p2 는 나이트가 이동하려고 하는 칸
            int p2x = sc.nextInt();
            int p2y = sc.nextInt();
            solve(boardLength, p1x, p1y, p2x, p2y);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        solution(args);
    }
}