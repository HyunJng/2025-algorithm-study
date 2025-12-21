import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] land;     // 현재 양분
    static int[][] A;        // 겨울에 추가되는 양분
    static ArrayList<Integer>[][] trees;

    // 8방향
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        land = new int[N][N];
        A = new int[N][N];
        trees = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(land[i], 5);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                trees[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees[x][y].add(age);
        }

        for (int year = 0; year < K; year++) {
            springAndSummer();
            fall();
            winter();
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += trees[i][j].size();
            }
        }
        System.out.println(answer);
    }

    static void springAndSummer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (trees[i][j].isEmpty()) continue;

                Collections.sort(trees[i][j]); // 어린 나무부터
                ArrayList<Integer> alive = new ArrayList<>();
                int deadFood = 0;

                for (int age : trees[i][j]) {
                    if (land[i][j] >= age) {
                        land[i][j] -= age;
                        alive.add(age + 1);
                    } else {
                        deadFood += age / 2;
                    }
                }

                land[i][j] += deadFood;
                trees[i][j] = alive;
            }
        }
    }

    static void fall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int age : trees[i][j]) {
                    if (age % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int ni = i + dx[d];
                            int nj = j + dy[d];
                            if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
                                trees[ni][nj].add(1);
                            }
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                land[i][j] += A[i][j];
            }
        }
    }
}
