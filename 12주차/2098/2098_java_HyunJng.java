import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        COST = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                COST[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }

    static int[][] dp; // dp[mask][i] = “현재까지 방문한 도시 집합이 mask이고, 현재 도시가 i일 때, 시작 도시(보통 0)
    static int[][] COST;
    static int FULL;
    static final int START = 0;
    static final int INF = 1_000_000_000; // 10억 정도면 충분
    public static int solution() {
        dp = new int[1 << N][N];
        FULL = (1 << N) - 1;

        for (int mask = 0; mask < (1 << N); mask++) {
            Arrays.fill(dp[mask], -1);
        }

        return dfs(1 << START, START);
    }

    static int dfs(int mask, int now) {
        // 1) 이미 계산한 상태면 바로 반환
        if (dp[mask][now] != -1) {
            return dp[mask][now];
        }

        // 2) 모든 도시 방문 완료 -> 시작점으로 복귀 비용
        if (mask == FULL) {
            if (COST[now][START] == 0) return INF;      // 돌아갈 길 없음
            return COST[now][START];
        }

        // 3) 다음 도시를 선택
        int best = INF;
        for (int next = 0; next < N; next++) {
            // 이미 방문했으면 스킵
            if ((mask & (1 << next)) != 0) continue;
            // 길 없으면 스킵
            if (COST[now][next] == 0) continue;

            int newMask = mask | (1 << next);
            int cost = COST[now][next] + dfs(newMask, next);
            if (cost < best) best = cost;
        }

        // 4) 메모하고 반환
        dp[mask][now] = best;
        return best;
    }
}