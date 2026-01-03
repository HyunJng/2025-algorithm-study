import java.io.*;
import java.util.*;

public class Main {
    private static int n;
    private static int m;
    private static int k;
    private static int[][] addNutrient;
    private static int[][] nutrient;
    private static List<Tree> trees = new ArrayList<>();
    private static Deque<Integer> deadIdx = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        init();

        for (int year = 0; year < k; year++) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(trees.size());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        addNutrient = new int[n][n];
        nutrient = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                addNutrient[i][j] = Integer.parseInt(line[j]);
                nutrient[i][j] = 5;
            }
        }

        for (int i = 0; i < m; i++) {
            String[] treeInfo = br.readLine().split(" ");
            int r = Integer.parseInt(treeInfo[0]) - 1;
            int c = Integer.parseInt(treeInfo[1]) - 1;
            int age = Integer.parseInt(treeInfo[2]);
            trees.add(new Tree(r, c, age));
        }

        trees.sort((t1, t2) -> t1.age - t2.age);
    }

    private static void spring() {
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);

            if (nutrient[tree.r][tree.c] < tree.age) {
                deadIdx.add(i);
                continue;
            }

            nutrient[tree.r][tree.c] -= tree.age;
            tree.age++;
        }
    }

    private static void summer() {
        while (!deadIdx.isEmpty()) {
            int idx = deadIdx.pollLast();
            Tree dead = trees.get(idx);

            nutrient[dead.r][dead.c] += dead.age / 2;
            dead.isDead = true;
        }
    }

    private static void fall() {
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        List<Tree> newTrees = new ArrayList<>();

        for (Tree tree : trees) {
            if (tree.isDead) continue;

            if (tree.age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nr = tree.r + dr[i];
                    int nc = tree.c + dc[i];

                    if (isValid(nr, nc)) {
                        newTrees.add(new Tree(nr, nc, 1));
                    }
                }
            }
        }

        for (Tree tree : trees) {
            if (!tree.isDead) {
                newTrees.add(tree);
            }
        }

        trees = newTrees;
    }

    private static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nutrient[i][j] += addNutrient[i][j];
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    static class Tree {
        int r;
        int c;
        int age;
        boolean isDead;

        Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
            this.isDead = false;
        }
    }
}