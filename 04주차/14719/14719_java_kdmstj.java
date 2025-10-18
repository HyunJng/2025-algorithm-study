package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans14719 {
    static int H, W;
    static int[] arr;
    static int answer = 0;
    static int startIdx = -1;

    public static void main(String[] args) throws IOException {
        input();

        simulation();

        System.out.println(answer);
    }

    private static void simulation() {
        // 왼쪽 벽 후보(내리막 시작점) 탐색
        for (int i = 0; i < W - 1; i++) {
            if (isDecrease(i)) {
                startIdx = i;
                break;
            }
        }
        if (startIdx == -1) return;

        while (startIdx < W - 1) {
            searchRange();
        }
    }

    private static void searchRange() {
        int startH = arr[startIdx];

        // 오른쪽에서 처음으로 startH 이상 만나는 지점 찾기
        int j = -1;
        for (int i = startIdx + 1; i < W; i++) {
            if (arr[i] >= startH) {
                j = i;
                break;
            }
        }

        if (j != -1) {
            int sum = 0;
            for (int idx = startIdx + 1; idx < j; idx++) {
                sum += startH - arr[idx];
            }
            answer += sum;
            startIdx = j;
            return;
        }

        // startH 보다 큰것이 없다면 오른쪽에서 젤 큰 값 찾기 (j == -1 인 경우)
        int k = startIdx + 1;
        for (int i = startIdx + 2; i < W; i++) {
            if (arr[i] > arr[k]) k = i;
        }

        int limitH = Math.min(startH, arr[k]); // 여기서는 보통 arr[k] (startH보다 낮음)
        int sum = 0;
        for (int idx = startIdx + 1; idx < k; idx++) {
            sum += limitH - arr[idx];
        }
        answer += sum;
        startIdx = k;
    }

    private static boolean isDecrease(int idx) {
        return arr[idx] > arr[idx + 1];
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        arr = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
