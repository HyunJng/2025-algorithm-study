// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int n, m, answer;
    static ArrayList<Integer> blockButton;
    static final int NOW = 100;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        blockButton = new ArrayList<>();
        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                blockButton.add(Integer.parseInt(st.nextToken()));
            }
        }

        // 방향키만으로 움직일수 있는 최소 횟수
        answer = Math.abs(NOW - n);

        for (int i = 0; i <= 1000000; i++) {
            String now = Integer.toString(i);
            boolean valid = true;
            for (int j = 0; j < now.length(); j++) {
                if (blockButton.contains(
                        Integer.parseInt(Character.toString(now.charAt(j))))) {
                    valid = false;
                    break;
                }
            }
            if (!valid)
                continue;
            answer = Math.min(answer, Math.abs(i - n) + now.length());
        }

        System.out.println(answer);
    }
}