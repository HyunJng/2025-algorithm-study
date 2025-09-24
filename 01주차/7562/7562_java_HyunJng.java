import java.util.*;
import java.io.*;

public class Main {
    public static Pair[] MOVEMENT = {
            new Pair(2, 1),
            new Pair(-2, -1),
            new Pair(1, 2),
            new Pair(-1, -2),
            new Pair(-1, 2),
            new Pair(1, -2),
            new Pair(-2, 1),
            new Pair(2, -1)
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int size = Integer.parseInt(br.readLine());
            String[] startStr = br.readLine().split(" ");
            String[] endStr = br.readLine().split(" ");
            Pair start = new Pair(Integer.parseInt(startStr[0]), Integer.parseInt(startStr[1]));
            Pair end = new Pair(Integer.parseInt(endStr[0]), Integer.parseInt(endStr[1]));

            System.out.println(solution(size, start, end));
        }
    }

    public static int solution(int size, Pair start, Pair end) {
        if(start.equals(end)) return 0;

        Queue<Pair> queue = new LinkedList<>();
        boolean[][] visited = new boolean[size][size];

        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Pair target = queue.poll();

            for (Pair move : MOVEMENT) {
                Pair next = target.move(move, target.depth + 1);
                if(!target.isAvailable(next, size) || visited[next.x][next.y]) continue;
                if(next.equals(end)) return next.depth;

                queue.add(next);
                visited[next.x][next.y] = true;
            }
        }
        return 0;
    }

    public static class Pair {
        int x;
        int y;
        int depth;

        public Pair(int x, int y) {
            this(x, y, 0);
        }

        public Pair(int x, int y, int depth) {
            this.x = x; this.y = y; this.depth = depth;
        }

        public boolean equals(Object o) {
            Pair other = (Pair) o;
            return this.x == other.x && this.y == other.y;
        }

        public Pair move(Pair other, int depth) {
            return new Pair(this.x + other.x, this.y + other.y, depth);
        }

        public boolean isAvailable(Pair next, int size) {
            return (0 <= next.x && next.x < size && 0 <= next.y && next.y < size);
        }
    }
}