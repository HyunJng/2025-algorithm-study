// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] t, p, dp;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        t = new int[N + 1];
        p = new int[N + 1];
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (t[i - j] <= j + 1) {
                    dp[i] = Math.max(Math.max(dp[i], dp[i - 1]), dp[i - j - 1] + p[i - j]);
                }
            }
        }
        System.out.println(dp[N]);
    }
}