// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static String a;
    static int b;
    static Deque<Character> stack;
    static LinkedHashSet<Integer> answers;
    static boolean[] visited;

    static void backTrack(int depth, int n) {
        if (depth == a.length()) {
            List<String> as = new ArrayList<>();
            stack.forEach((el) -> as.add(el.toString()));
            answers.add(Integer.parseInt(String.join("", as)));
            return;
        }

        for (int i = 0; i < n; i++) {
            // 맨앞에 0이 있을수 없음
            if (stack.isEmpty() && a.charAt(i) == '0')
                continue;
            if (visited[i])
                continue;

            visited[i] = true;
            stack.add(a.charAt(i));
            backTrack(depth + 1, n);
            visited[i] = false;
            stack.pollLast();
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = st.nextToken();
        b = Integer.parseInt(st.nextToken());
        visited = new boolean[11];
        Arrays.fill(visited, false);
        stack = new LinkedList<>();
        answers = new LinkedHashSet<>();

        backTrack(0, a.length());
        ArrayList<Integer> answerList = new ArrayList<>(answers);
        answerList.sort(Integer::compare);

        if (answerList.get(0) > b) {
            System.out.println(-1);
            return;
        }

        int i = 0;
        while (i < answerList.size()) {
            if (i > 0 && answerList.get(i) > b) {
                System.out.println(answerList.get(i - 1));
                return;
            }
            i++;
        }
        if (answerList.get(i - 1) < b) {
            System.out.println(answerList.get(i - 1));
            return;
        }

        System.out.println(-1);
    }
}