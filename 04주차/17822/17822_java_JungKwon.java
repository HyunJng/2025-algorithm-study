package week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17822 {
    static int N, M, T;
    static int[][] disk;
    static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nmt = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = nmt[0];
        M = nmt[1];
        T = nmt[2];

        disk = new int[N][M];
        for (int i = 0; i < N; i++) {
            disk[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (int t = 0; t < T; t++) {
            int[] cmd = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int x = cmd[0];
            int d = cmd[1];
            int k = cmd[2];

            rotateDisks(x, d, k);

            if (!removeAdjacent()) {
                adjustByAverage();
            }
        }

        System.out.println(sumDisk());
    }

    static void rotateDisks(int target, int direction, int step) {
        for (int i = target - 1; i < N; i += target) {
            int[] rotated = new int[M];
            for (int j = 0; j < M; j++) {
                int nextIndex = (direction == 0) ? (j + step) % M : (j - step + M) % M;
                rotated[nextIndex] = disk[i][j];
            }
            disk[i] = rotated;
        }
    }

    // 인접한 동일 숫자 제거
    static boolean removeAdjacent() {
        boolean removed = false;
        boolean[][] visited = new boolean[N][M];
        List<int[]> remove = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (disk[r][c] == 0 || visited[r][c]) continue;

                int value = disk[r][c];
                Queue<int[]> q = new ArrayDeque<>();
                List<int[]> same = new ArrayList<>();

                q.add(new int[]{r, c});
                visited[r][c] = true;
                same.add(new int[]{r, c});

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    for (int[] dir : DIRS) {
                        int nr = cur[0] + dir[0];
                        int nc = (cur[1] + dir[1] + M) % M;
                        if (nr < 0 || nr >= N || visited[nr][nc]) continue;
                        if (disk[nr][nc] == value) {
                            visited[nr][nc] = true;
                            q.add(new int[]{nr, nc});
                            same.add(new int[]{nr, nc});
                        }
                    }
                }

                if (same.size() > 1) {
                    removed = true;
                    remove.addAll(same);
                }
            }
        }

        for (int[] pos : remove) {
            disk[pos[0]][pos[1]] = 0;
        }
        return removed;
    }

    // 인접 제거가 없는 경우, 평균 기준으로 숫자 조정
    static void adjustByAverage() {
        int sum = 0, count = 0;
        for (int[] row : disk) {
            for (int v : row) {
                if (v > 0) {
                    sum += v;
                    count++;
                }
            }
        }
        if (count == 0) return;
        double avg = (double) sum / count;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (disk[i][j] == 0) continue;
                if (disk[i][j] > avg) disk[i][j]--;
                else if (disk[i][j] < avg) disk[i][j]++;
            }
        }
    }

    // 남은 모든 수 합
    static int sumDisk() {
        return Arrays.stream(disk)
                .flatMapToInt(Arrays::stream)
                .sum();
    }
}