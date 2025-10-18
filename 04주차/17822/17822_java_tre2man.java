// package org.example;

import java.io.*;

public class Main {
    static int N, M, T;
    static int[][] circle;

    // x의 배수 원판을 d 방향으로 k칸 회전
    static void rotate(int x, int d, int k) {
        for (int i = x; i <= N; i += x) {
            int[] temp = new int[M];
            for (int j = 0; j < M; j++) {
                // 시계 방향
                if (d == 0) {
                    temp[(j + k) % M] = circle[i][j];
                }
                // 반시계 방향
                else {
                    temp[(j - k + M) % M] = circle[i][j];
                }
            }
            circle[i] = temp;
        }
    }

    // 인접하면서 같은 수 찾아서 제거
    static boolean findAndRemove() {
        boolean[][] toRemove = new boolean[N + 1][M];
        boolean found = false;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (circle[i][j] == 0)
                    continue;

                // 같은 원판 내에서 인접한 수 확인
                int left = (j - 1 + M) % M;
                int right = (j + 1) % M;

                if (circle[i][j] == circle[i][left]) {
                    toRemove[i][j] = true;
                    toRemove[i][left] = true;
                    found = true;
                }
                if (circle[i][j] == circle[i][right]) {
                    toRemove[i][j] = true;
                    toRemove[i][right] = true;
                    found = true;
                }

                // 인접한 원판과 같은 위치 확인
                if (i > 1 && circle[i][j] == circle[i - 1][j]) {
                    toRemove[i][j] = true;
                    toRemove[i - 1][j] = true;
                    found = true;
                }
                if (i < N && circle[i][j] == circle[i + 1][j]) {
                    toRemove[i][j] = true;
                    toRemove[i + 1][j] = true;
                    found = true;
                }
            }
        }

        // 표시된 위치를 0으로 변경
        if (found) {
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (toRemove[i][j]) {
                        circle[i][j] = 0;
                    }
                }
            }
        }
        return found;
    }

    // 평균을 기준으로 조정
    static void updateByAverage() {
        int sum = 0;
        int count = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (circle[i][j] > 0) {
                    sum += circle[i][j];
                    count++;
                }
            }
        }

        // 변경되지 않았으면 로직 종료
        if (count == 0) {
            return;
        }

        double avg = (double) sum / count;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (circle[i][j] > 0) {
                    if (circle[i][j] > avg) {
                        circle[i][j]--;
                    } else if (circle[i][j] < avg) {
                        circle[i][j]++;
                    }
                }
            }
        }
    }

    // 전체 수의 합 출력
    static int getSum() {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                sum += circle[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);

        circle = new int[N + 1][M];
        for (int i = 1; i <= N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                circle[i][j] = Integer.parseInt(line[j]);
            }
        }

        for (int t = 0; t < T; t++) {
            String[] cmd = br.readLine().split(" ");
            int x = Integer.parseInt(cmd[0]);
            int d = Integer.parseInt(cmd[1]);
            int k = Integer.parseInt(cmd[2]);

            // 1. x의 배수인 원판을 d 방향으로 k칸 회전
            rotate(x, d, k);

            // 2. 인접하면서 같은 수 찾기
            boolean found = findAndRemove();

            // 3. 같은 수가 없으면 평균 계산
            if (!found) {
                updateByAverage();
            }
        }

        System.out.println(getSum());
    }
}