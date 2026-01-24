import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static final int HOME = 1, CHICKEN_RESTAURANT = 2, EMPTY = 0;
    static int[][] map, candidate;
    static List<Point> home = new ArrayList<>();
    static List<Point> chickenRestaurant = new ArrayList<>();
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                map[i][j] = temp;
                if(temp == HOME) home.add(new Point(j, i));
                if(temp == CHICKEN_RESTAURANT) chickenRestaurant.add(new Point(j, i));
            }
        }
        candidate = new int[chickenRestaurant.size()][home.size()];

        solution();
        System.out.println(result);
    }

    public static void solution() {
        calculateChickenDistance();
        combination(0, 0, new ArrayList<>());
    }


    public static void combination(int level, int index, List<Integer> target) {
        if(level == M){
            calculateMinCase(target);
            return;
        }

        for (int i = index; i < chickenRestaurant.size(); i++) {
            target.add(i);
            combination(level + 1, i + 1, target);
            target.remove(target.size() - 1);
        }
    }

    public static void calculateMinCase(List<Integer> target) {
        int sum = 0;
        for (int j = 0; j < home.size(); j++) {
            int min = Integer.MAX_VALUE;
            for (int i : target) {
                if(min > candidate[i][j]) min = candidate[i][j];
            }
            sum += min;
        }
        result = Math.min(sum, result);
    }

    public static void calculateChickenDistance() {
        for (int i = 0; i < chickenRestaurant.size(); i++) {
            Point target = chickenRestaurant.get(i);
            for (int j = 0; j < home.size(); j++) {
                candidate[i][j] = Point.calculateDistance(target, home.get(j));
            }
        }
    }

    public static class Point{
        int x, y;

        Point(int x, int y) {
            this.x = x; this.y = y;
        }

        public static int calculateDistance(Point a, Point b) {
            return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
        }
    }
}