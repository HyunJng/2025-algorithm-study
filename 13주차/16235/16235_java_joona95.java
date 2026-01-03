import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static final int DEFAULT_NUTRIENT = 5;
    public static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static int n, m, k;
    public static int[][] add;
    public static int[][] nutrients;
    public static int[][] deads;
    public static Deque<Integer>[][] trees;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        add = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        trees = new ArrayDeque[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                trees[i][j] = new ArrayDeque<>();
            }
        }

        nutrients = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(nutrients[i], DEFAULT_NUTRIENT);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees[x][y].addFirst(z);
        }

        deads = new int[n + 1][n + 1];

        bw.write(String.valueOf(solution(k)));

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution(int k) {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (trees[i][j].isEmpty()) {
                    continue;
                }

                ArrayList<Integer> tmp = new ArrayList<>(trees[i][j]);
                Collections.sort(tmp);
                trees[i][j].clear();
                for (int age : tmp) trees[i][j].addLast(age);
            }
        }

        for (int i = 0; i < k; i++) {
            spring();
            summer();
            fall();
            winter();
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                answer += trees[i][j].size();
            }
        }
        return answer;
    }

    private static void spring() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int size = trees[i][j].size();
                for (int l = 0; l < size; l++) {
                    int age = trees[i][j].pollFirst();
                    if (nutrients[i][j] >= age) {
                        nutrients[i][j] -= age;
                        trees[i][j].addLast(age + 1);
                    } else {
                        deads[i][j] += age / 2;
                    }
                }
            }
        }
    }

    private static void summer() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                nutrients[i][j] += deads[i][j];
                deads[i][j] = 0;
            }
        }
    }

    private static void fall() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int tree : trees[i][j]) {
                    if (tree % 5 == 0) {
                        for (int l = 0; l < dx.length; l++) {
                            int nx = i + dx[l];
                            int ny = j + dy[l];
                            if (nx <= 0 || nx > n || ny <= 0 || ny > n) {
                                continue;
                            }
                            trees[nx][ny].addFirst(1);
                        }
                    }
                }
            }
        }
    }

    private static void winter() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                nutrients[i][j] += add[i][j];
            }
        }
    }
}