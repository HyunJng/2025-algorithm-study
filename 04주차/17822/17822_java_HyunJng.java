import java.util.*;
import java.io.*;

public class Main {
    static int N, M, T;
    static int[][] arr;
    static boolean[][] isNotAlive;
    static int[] start;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        isNotAlive = new boolean[N][M];
        start = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int time = 0; time < T; time++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            rotate(x, d, k);

            if (!checkAlive()) {
                double[] agvInfo = deleteAndSum();
                double avg = agvInfo[1] > 0 ? agvInfo[0] / agvInfo[1] : 0;
                calculateByAvg(avg);
            } else {
                deleteAndSum();
            }
        }

        System.out.print((int) deleteAndSum()[0]);
    }

    public static int circularIndex(int a){
        return a >= 0 ? a % M : M + a;
    }

    public static void rotate(int x, int d, int k) {
        for (int i = x - 1; i < N; i += x) {
            switch (d) {
                case 0:
                    start[i] = circularIndex(start[i] - k); break;
                case 1:
                    start[i] = circularIndex(start[i] + k); break;
            }
        }
    }

    public static boolean checkAlive() {
        int[] beforeM = new int[N];
        boolean isDelete = false;

        for (int m = 0; m <= M; m++) {
            int beforeNValue = 0;
            int beforeNIndex = 0;
            for (int n = 0; n < N; n++) {
                int findM = circularIndex(start[n] + m);
                if(arr[n][findM] != 0) {
                    if (beforeNValue == arr[n][findM]) {
                        isNotAlive[n][findM] = isNotAlive[n - 1][beforeNIndex] = true;
                        isDelete = true;
                    }
                    if (beforeM[n] == arr[n][findM]) {
                        isNotAlive[n][findM] = isNotAlive[n][circularIndex(findM - 1)] = true;
                        isDelete = true;
                    }
                }
                beforeNValue = beforeM[n] = arr[n][findM];
                beforeNIndex = findM;
            }
        }
        return isDelete;
    }

    public static double[] deleteAndSum() {
        double sum = 0, cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isNotAlive[i][j]) {
                    sum += arr[i][j];
                    cnt++;
                } else {
                    arr[i][j] = 0;
                }
            }
        }
        return new double[]{sum, cnt};
    }

    public static void calculateByAvg(double avg) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isNotAlive[i][j]) {
                    arr[i][j] += arr[i][j] > avg ? -1 : (arr[i][j] == avg)? 0 :1;
                }
            }
        }
    }
}