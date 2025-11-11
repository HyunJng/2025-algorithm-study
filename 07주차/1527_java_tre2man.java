// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int n, m, answer;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = 0;

        for (int i = n; i <= m; i++) {
            int now = i;
            boolean isAnswer = true;
            while (now >= 1) {
                if (now % 10 != 4 && now % 10 != 7) {
                    isAnswer = false;
                    break;
                }
                now /= 10;
            }
            if (isAnswer)
                answer++;
        }

        System.out.println(answer);
    }
}