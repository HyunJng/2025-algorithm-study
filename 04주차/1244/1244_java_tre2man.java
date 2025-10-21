package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static public void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        // 1-based index
        int[] arr = new int[n + 1];
        String[] strArr = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(strArr[i - 1]);
        }
        int caseNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseNum; i++) {
            int[] oneCase = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int gender = oneCase[0];
            int order = oneCase[1];
            // 남자
            if (gender == 1) {
                for (int j = 1; j * order <= n; j++) {
                    arr[j * order] = Math.abs(arr[j * order] - 1);
                }
            }
            // 여자
            else {
                for (int j = 0; order - j >= 1 && order + j <= n; j++) {
                    int k = order + j;
                    int l = order - j;
                    if (arr[k] == arr[l]) {
                        if (j == 0) {
                            arr[k] = Math.abs(arr[k] - 1);
                        } else {
                            arr[k] = Math.abs(arr[k] - 1);
                            arr[l] = Math.abs(arr[l] - 1);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(arr[i]);
            sb.append(" ");
            if (i % 20 == 0) {
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}