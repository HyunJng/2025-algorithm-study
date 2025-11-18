import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] office;

    static int[][] coverage;

    static int minBlindSpot = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];

        office = new int[N][M];
        coverage = new int[N][M];
        for (int i = 0; i < N; i++) {
            office[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        backtrack(0, 0);

        System.out.println(minBlindSpot);
    }

    static void backtrack(int y, int x) {
        if (x == M) {
            y++;
            x = 0;
        }

        if (y == N) {
            count_zero();
            return;
        }

        if(office[y][x] == 0 || office[y][x] == 6) {
            backtrack(y, x + 1);
        } else if (office[y][x] == 1) {
            for (int i = 0; i < 4; i++) {
                apply_cctv_type1(y, x, i);
                backtrack(y, x + 1);
                rollback_cctv_type1(y, x, i);
            }
        } else if (office[y][x] == 2) {
            for (int i = 0; i < 2; i++) {
                apply_cctv_type2(y, x, i);
                backtrack(y, x + 1);
                rollback_cctv_type2(y, x, i);
            }
        } else if (office[y][x] == 3) {
            for (int i = 0; i < 4; i++) {
                apply_cctv_type3(y, x, i);
                backtrack(y, x + 1);
                rollback_cctv_type3(y, x, i);
            }
        } else if (office[y][x] == 4) {
            for (int i = 0; i < 4; i++) {
                apply_cctv_type4(y, x, i);
                backtrack(y, x + 1);
                rollback_cctv_type4(y, x, i);
            }
        } else if (office[y][x] == 5) {
            apply_cctv_type5(y, x);
            backtrack(y, x + 1);
            rollback_cctv_type5(y, x);
        }
    }

    static void count_zero() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (coverage[i][j] == 0 && office[i][j] == 0) count++;
            }
        }

        minBlindSpot = Math.min(count, minBlindSpot);
    }

    static void apply_cctv_type1(int y, int x, int direction) {
        if (direction == 0) {
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]++; // 오른쪽
        } else if (direction == 1) {
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]++; // 아래쪽
        } else if (direction == 2) {
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]++; // 왼쪽
        } else if (direction == 3) {
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]++; // 위쪽
        }
    }

    static void rollback_cctv_type1(int y, int x, int direction) {
        if (direction == 0) {
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]--; // 오른쪽
        } else if (direction == 1) {
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]--; // 아래쪽
        } else if (direction == 2) {
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]--; // 왼쪽
        } else if (direction == 3) {
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]--; // 위쪽
        }
    }

    static void apply_cctv_type2(int y, int x, int direction) {
        if (direction == 0) {
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]++; // 오른쪽
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]++; // 왼쪽
        } else if (direction == 1) {
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]++; // 아래쪽
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]++; // 위쪽
        }
    }

    static void rollback_cctv_type2(int y, int x, int direction) {
        if (direction == 0) {
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]--; // 오른쪽
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]--; // 왼쪽
        } else if (direction == 1) {
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]--; // 아래쪽
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]--; // 위쪽
        }
    }

    static void apply_cctv_type3(int y, int x, int direction) {
        if (direction == 0) {
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]++; // 오른쪽
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]++; // 아래쪽
        } else if (direction == 1) {
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]++; // 아래쪽
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]++; // 왼쪽
        } else if (direction == 2) {
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]++; // 왼쪽
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]++; // 위쪽
        } else if (direction == 3) {
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]++; // 위쪽
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]++; // 오른쪽
        }
    }

    static void rollback_cctv_type3(int y, int x, int direction) {
        if (direction == 0) {
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]--; // 오른쪽
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]--; // 아래쪽
        } else if (direction == 1) {
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]--; // 아래쪽
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]--; // 왼쪽
        } else if (direction == 2) {
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]--; // 왼쪽
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]--; // 위쪽
        } else if (direction == 3) {
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]--; // 위쪽
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]--; // 오른쪽
        }
    }

    static void apply_cctv_type4(int y, int x, int direction) {
        if (direction == 0) {
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]++; // 위쪽
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]++; // 오른쪽
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]++; // 아래쪽
        } else if (direction == 1) {
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]++; // 오른쪽
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]++; // 아래쪽
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]++; // 왼쪽
        } else if (direction == 2) {
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]++; // 아래쪽
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]++; // 왼쪽
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]++; // 위쪽
        } else if (direction == 3) {
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]++; // 왼쪽
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]++; // 위쪽
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]++; // 오른쪽
        }
    }

    static void rollback_cctv_type4(int y, int x, int direction) {
        if (direction == 0) {
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]--; // 위쪽
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]--; // 오른쪽
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]--; // 아래쪽
        } else if (direction == 1) {
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]--; // 오른쪽
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]--; // 아래쪽
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]--; // 왼쪽
        } else if (direction == 2) {
            for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]--; // 아래쪽
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]--; // 왼쪽
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]--; // 위쪽
        } else if (direction == 3) {
            for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]--; // 왼쪽
            for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]--; // 위쪽
            for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]--; // 오른쪽
        }
    }

    static void apply_cctv_type5(int y, int x) {
        for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]++; // 오른쪽
        for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]++; // 왼쪽
        for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]++; // 위쪽
        for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]++; // 아래쪽
    }

    static void rollback_cctv_type5(int y, int x) {
        for (int i = x+1; i < M && office[y][i] != 6; i++) coverage[y][i]--; // 오른쪽
        for (int i = x-1; i >= 0 && office[y][i] != 6; i--) coverage[y][i]--; // 왼쪽
        for (int i = y-1; i >= 0 && office[i][x] != 6; i--) coverage[i][x]--; // 위쪽
        for (int i = y+1; i < N && office[i][x] != 6; i++) coverage[i][x]--; // 아래쪽
    }
}