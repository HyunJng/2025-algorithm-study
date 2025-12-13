// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] graph;
    static int[][] dp;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = dfs(0, 1);
        System.out.println(answer);
    }

    static int dfs(int x, int visited) {
        if (visited == (1 << N) - 1) {
            if (graph[x][0] == 0) {
                return INF;
            }
            return graph[x][0];
        }

        if (dp[x][visited] != 0) {
            return dp[x][visited];
        }

        dp[x][visited] = INF;

        for (int i = 1; i < N; i++) {
            if (graph[x][i] == 0) {
                continue;
            }
            if ((visited & (1 << i)) != 0) {
                continue;
            }

            int nextVisited = visited | (1 << i);
            int cost = dfs(i, nextVisited) + graph[x][i];
            if (cost < dp[x][visited]) {
                dp[x][visited] = cost;
            }
        }

        return dp[x][visited];
    }
}