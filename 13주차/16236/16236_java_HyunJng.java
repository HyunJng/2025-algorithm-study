import java.io.*;
import java.util.*;

public class Main {
    public static class Shark {
        int x, y, size, eaten;

        Shark(int x, int y) {
            this.x = x;
            this.y = y;
            size = 2;
        }

        public boolean levelUp(Fish fish) {
            this.x = fish.x;
            this.y = fish.y;
            if (++eaten == size) {
                size++;
                eaten = 0;
                return true;
            }
            return false;
        }
    }

    public static class Fish implements Comparable<Fish> {
        int x, y;
        int distance;

        Fish(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Fish o) {
            if (this.distance == o.distance) {
                if (this.y == o.y) {
                    return Integer.compare(this.x, o.x);
                }
                return Integer.compare(this.y, o.y);
            }
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static int N;
    public static int[][] map;
    public static Shark shark;
    public static final int EMPTY = 0, SHARK = 9;
    public static final Map<Integer, List<Fish>> fishes = new HashMap<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == SHARK) {
                    shark = new Shark(j, i);
                    continue;
                }
                fishes.computeIfAbsent(temp, n -> new ArrayList<>()).add(new Fish(j, i));
                map[i][j] = temp;
            }
        }

        System.out.println(solution());
    }

    public static int solution() {
        if (!fishes.containsKey(shark.size - 1)) return 0;
        List<Fish> candidate = new LinkedList<>(fishes.get(shark.size - 1));

        int result = 0;
        while (!candidate.isEmpty()) {
            int[][] distanceMap = makeDistanceMap();
            candidate.forEach(c -> c.distance = (distanceMap[c.y][c.x] != 0) ? distanceMap[c.y][c.x] : Integer.MAX_VALUE);

            Collections.sort(candidate);
            Fish target = candidate.get(0);
            if (target.distance == Integer.MAX_VALUE) break;

            candidate.remove(0);
            map[target.y][target.x] = EMPTY;
            if (shark.levelUp(target) && fishes.containsKey(shark.size - 1)) {
                candidate.addAll(fishes.get(shark.size - 1));
            }
            result += target.distance;
        }
        return result;
    }

    public static int[][] makeDistanceMap() {
        int[][] result = new int[N][N];
        boolean[][] visited = new boolean[N][N];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{shark.x, shark.y, 1});
        visited[shark.y][shark.x] = true;

        while (!queue.isEmpty()) {
            int[] target = queue.poll();
            for (int i = 0; i < 4; i++) {
                int tx = target[0] + dx[i], ty = target[1] + dy[i];
                if (0 > tx || tx >= N || 0 > ty || ty >= N) continue;
                if (visited[ty][tx] || shark.size < map[ty][tx]) continue;
                result[ty][tx] = target[2];
                visited[ty][tx] = true;
                queue.add(new int[]{tx, ty, target[2] + 1});
            }
        }
        return result;
    }
}