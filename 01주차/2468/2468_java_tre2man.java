import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static Integer getSafeAreaCount(int[][] map, int n, int height) {
        // 방문 기록을 미리 남겨 다시 탐색하지 않게 함
        int[][] visited = new int[n][n];
        int safeAreaCount = 0;

        // 모든 좌표에 대해서 bfs 시작
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                // 방문한 좌표면 패스
                if (visited[x][y] != 0) {
                    continue;
                }
                // 잠긴 곳이면 패스 (접근 못함)
                if (map[x][y] - height <= 0) {
                    continue;
                }

                // 새 영역마다 카운트를 올림
                safeAreaCount++;
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[] { x, y });

                while (!q.isEmpty()) {
                    int[] point = q.poll();
                    int sx = point[0];
                    int sy = point[1];

                    int[] dx = new int[] { -1, 1, 0, 0 };
                    int[] dy = new int[] { 0, 0, -1, 1 };

                    for (int i = 0; i < 4; i++) {
                        int nx = sx + dx[i];
                        int ny = sy + dy[i];

                        // 좌표 벗어나면 패스
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                            continue;
                        }

                        // 방문한 좌표면 패스
                        if (visited[nx][ny] != 0) {
                            continue;
                        }

                        // 잠긴 곳이면 패스 (접근 못함)
                        if (map[nx][ny] - height <= 0) {
                            continue;
                        }

                        // 방문 진행
                        visited[nx][ny] = safeAreaCount;
                        q.add(new int[] { nx, ny });
                    }
                }
            }
        }

        return safeAreaCount;
    }

    static void solution(String[] args) throws FileNotFoundException {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);

        // 입력 파싱
        int n = sc.nextInt();
        int maxHeight = 0;
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int height = sc.nextInt();
                map[i][j] = height;
                if (maxHeight < height) {
                    maxHeight = height;
                }
            }
        }

        // 풀이
        int answer = 0;
        // 모든 높이에 대해서 bfs 실행
        for (int height = 0; height < maxHeight; height++) {
            int safeAreaCount = getSafeAreaCount(map, n, height);
            if (answer < safeAreaCount) {
                answer = safeAreaCount;
            }
        }
        System.out.println(answer);
    }

    /**
     * bfs 로 모든 좌표와 모든 높이를 탐색하는 문제.
     * 최대 높이보다 더 탐색하지 않게 한다.
     */
    public static void main(String[] args) throws FileNotFoundException {
        solution(args);
    }
}