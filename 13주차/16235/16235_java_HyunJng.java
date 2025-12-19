import java.io.*;
import java.util.*;

public class Main {
    public static class Tree implements Comparable<Tree> {
        int x, y;
        int age;

        Tree(int y, int x, int age) {
            this.y = y; this.x = x;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }

    static int N, M, K;
    static PriorityQueue<Tree> trees = new PriorityQueue<>();
    static int[][] nutrient, s2d2;
    static final int[] new_x = {1, 1, 0, -1, -1, -1, 0, 1};
    static final int[] new_y = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        s2d2 = new int[N + 1][N + 1];
        nutrient = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                s2d2[i][j] = Integer.parseInt(st.nextToken());
                nutrient[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            trees.add(new Tree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        System.out.println(solution());
    }

    public static int solution() {
        PriorityQueue<Tree> next = new PriorityQueue<>();
        PriorityQueue<Tree> die = new PriorityQueue<>();
        for(int i = 0; i < K; i++) {
            while (!trees.isEmpty()) {
                Tree target = trees.poll();
                if (nutrient[target.y][target.x] >= target.age) {
                    nutrient[target.y][target.x] -= target.age;
                    target.age++;
                    next.add(target);
                } else {
                    die.add(target);
                }
            }

            while (!die.isEmpty()) {
                Tree target = die.poll();
                nutrient[target.y][target.x] += target.age / 2;
            }

            while (!next.isEmpty()) {
                Tree target = next.poll();
                if (target.age % 5 == 0) {
                    for (int j = 0; j < 8; j++) {
                        int ny = target.y + new_y[j], nx = target.x + new_x[j];
                        if(!inRange(ny) || !inRange(nx)) continue;
                        trees.add(new Tree(ny, nx, 1));
                    }
                }
                trees.add(target);
            }
            addNutrient();
        }

        return trees.size();
    }

    public static void addNutrient() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                nutrient[i][j] += s2d2[i][j];
            }
        }
    }

    public static boolean inRange(int xy) {
        return 0 < xy && xy <= N;
    }
}