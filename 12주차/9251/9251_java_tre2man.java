// package org.example;

import java.io.*;

public class Main {
    static String a, b;
    static int al, bl;
    static int dp[][];

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();
        al = a.length();
        bl = b.length();
        dp = new int[al + 1][bl + 1];

        for (int i = 1; i <= al; i++) {
            for (int j = 1; j <= bl; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[al][bl]);
    }
}