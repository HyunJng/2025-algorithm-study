import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] W;          // 비용 배열
    static int[][] dp;         // dp[curr][visited]
    static final int INF = 1_000_000_000;

    // curr : 현재 도시
    // visited : 방문한 도시 비트마스크
    static int tsp(int curr, int visited) {

        // 모든 도시를 방문한 경우
        if (visited == (1 << N) - 1) {
            if (W[curr][0] != 0) {
                return W[curr][0]; // 출발 도시로 복귀
            }
            return INF; // 돌아갈 수 없음
        }

        // 이미 계산한 적이 있다면 그대로 사용
        if (dp[curr][visited] != -1) {
            return dp[curr][visited];
        }

        int minCost = INF;

        // 다음 도시 선택
        for (int next = 0; next < N; next++) {

            // 길이 없으면 패스
            if (W[curr][next] == 0) continue;

            // 이미 방문했으면 패스
            if ((visited & (1 << next)) != 0) continue;

            int nextVisited = visited | (1 << next);
            int cost = W[curr][next] + tsp(next, nextVisited);

            minCost = Math.min(minCost, cost);
        }

        // dp에 저장
        dp[curr][visited] = minCost;
        return minCost;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        W = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 0번 도시에서 시작, 방문 상태 = 000...001
        System.out.println(tsp(0, 1));
    }
}
