package 12주차.2098;

public class 2098_java_Kyounglin {
    static final int INF = 1_000_000_000;
    static int n;
    static int[][] w;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        w = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[1 << n][n];
        for (int i = 0; i < (1 << n); i++) {
            Arrays.fill(dp[i], -1);
        }

        int result = tsp(1, 0); 
        System.out.println(result);
    }

    static int tsp(int visited, int cur) {
        if (visited == (1 << n) - 1) {
            if (w[cur][0] == 0) return INF;  
            return w[cur][0];
        }

        if (dp[visited][cur] != -1) return dp[visited][cur];

        int ret = INF;

        for (int next = 0; next < n; next++) {

            if ((visited & (1 << next)) != 0) continue;

            if (w[cur][next] == 0) continue;

            int newVisited = visited | (1 << next);
            int cost = W[cur][next] + tsp(newVisited, next);

            ret = Math.min(ret, cost);
        }

        return dp[visited][cur] = ret;
    }
    
}
