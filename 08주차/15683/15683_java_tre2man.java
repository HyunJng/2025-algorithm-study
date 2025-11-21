// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    // n, m, 빈 공간 (0의 수), 답
    static int n, m, empty, answer;
    static int[][] map;
    // x, y, 방향 (0~3)
    static ArrayList<int[]> cctvLocation;
    // x, y 방향
    static final int[][][] cctv = new int[][][] {
            {},
            { { 1, 0 } },
            { { 1, 0 }, { -1, 0 } },
            { { 0, 1 }, { 1, 0 } },
            { { -1, 0 }, { 0, 1 }, { 1, 0 } },
            { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } },
    };

    // 특정 cctv 를 turnCount 만큼 회전한 결과를 반환, 원본 객체 손상 안가게 처리
    static int[][] turn(int[][] target, int turnCount) {
        // 새 배열 생성
        int[][] result = new int[target.length][2];
        for (int i = 0; i < target.length; i++) {
            result[i] = Arrays.copyOf(target[i], 2);
        }

        // 회전수 만큼 회전하기
        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < turnCount; j++) {
                int temp = result[i][0];
                result[i][0] = result[i][1];
                result[i][1] = -temp;
            }
        }

        return result;
    }

    static void checkAnswer() {
        boolean[][] visited = new boolean[n][m];

        // 각 cctv 마다 확인하기
        for (int i = 0; i < cctvLocation.size(); i++) {
            int[] nowCctv = cctvLocation.get(i);
            // 해당 방향으로 회전한 cctv 를 기준으로 사각지대 확인하기
            int[][] afterTurn = turn(
                    cctv[map[nowCctv[1]][nowCctv[0]]],
                    nowCctv[2]);
            // 각 방향에 대해서 확인
            for (int j = 0; j < afterTurn.length; j++) {
                int[] nowDirection = afterTurn[j];
                for (int k = 1;; k++) {
                    int nx = nowCctv[0] + k * nowDirection[0];
                    int ny = nowCctv[1] + k * nowDirection[1];
                    // 좌표 벗어나면 잔행 못함
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                        break;
                    // 벽이 있는 경우에는 진행 못함
                    if (map[ny][nx] == 6)
                        break;
                    // cctv 는 통과 가능, 볼수 있는 곳은 아님
                    if (map[ny][nx] > 0 && map[ny][nx] <= 5)
                        continue;
                    visited[ny][nx] = true;
                }
            }
        }
        // 중복으로 보이는 공간이 있을수 있으므로 visited 된 곳을 확인
        int canSee = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j])
                    canSee++;
            }
        }
        answer = Math.min(answer, empty - canSee);
    }

    // 현재 cctv 의 모든 방향에 대해서 탐색하기
    static void backTrack(int depth) {
        if (depth == cctvLocation.size()) {
            // 추적 시작
            checkAnswer();
            return;
        }
        for (int i = 0; i < 4; i++) {
            backTrack(depth + 1);
            // 방향은 0~3까지만 유효하다
            cctvLocation.get(depth)[2] = ++cctvLocation.get(depth)[2] % 4;
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        empty = 0;
        answer = Integer.MAX_VALUE;
        cctvLocation = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    empty++;
                } else if (map[i][j] > 0 && map[i][j] <= 5) {
                    cctvLocation.add(new int[] { j, i, 0 });
                }
            }
        }
        backTrack(0);
        System.out.println(answer);
    }
}