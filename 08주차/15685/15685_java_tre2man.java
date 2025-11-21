// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static final int SIZE = 101;
    static boolean[][] map = new boolean[SIZE][SIZE];

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<int[]> dragon = new ArrayList<>();
            dragon.add(new int[] { x, y });

            switch (d) {
                case 0:
                    dragon.add(new int[] { x + 1, y });
                    break;
                case 1:
                    dragon.add(new int[] { x, y - 1 });
                    break;
                case 2:
                    dragon.add(new int[] { x - 1, y });
                    break;
                default:
                    dragon.add(new int[] { x, y + 1 });
                    break;
            }

            for (int j = 0; j < g; j++) {
                int[] ref = dragon.get(dragon.size() - 1);
                int rx = ref[0], ry = ref[1];

                /*
                 * 입력 배열을 받아서 좌표 기준으로 시계 방향으로 90도 회전한 배열 반환, 복사하여 반환한다.
                 * 
                 * 특정 좌표를 기준으로 90도 시계 방향으로 돌리는 방법의 점화식은 다음과 같이 구한다.
                 * 
                 * a, b : 시작 좌표
                 * c, d : 목표 좌표
                 * e, f : 기준 좌표
                 * 
                 * 기준 좌표를 0에 맞추기 위하여 모든 좌표에 -e, -f 를 한다.
                 * a - e, b - f : 시작 좌표
                 * c - e, d - f : 목표 좌표
                 * 0, 0 : 기준 좌표
                 * 
                 * 시작 좌표를 90도 돌리는 방법은 x,y 를 바꾸고 x 자리에 -1 을 곱한다. 이렇게 하면 다음 등식이 나온다.
                 * c - e = f - b
                 * d - f = a - e
                 * 
                 * 이제 해당 등식을 c, d 로 몬다.
                 * c = f + e - b
                 * d = f - e + a
                 */
                for (int idx = dragon.size() - 2; idx >= 0; idx--) {
                    int[] p = dragon.get(idx);
                    int px = p[0], py = p[1];
                    int nx = rx + ry - py;
                    int ny = ry - rx + px;
                    dragon.add(new int[] { nx, ny });
                }
            }

            for (int[] p : dragon) {
                int px = p[0], py = p[1];
                if (0 <= px && px <= 100 && 0 <= py && py <= 100) {
                    map[py][px] = true;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}