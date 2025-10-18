import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int n, m, t;
    private static int[][] arr;

    public void solution(int x, int d, int k) {
        rotate(x, d, k);
        removeOrAdjust();
    }

    private void rotate(int x, int d, int k) {
        for (int i = 0; i < n; i++) {
            if ((i + 1) % x != 0) {
                continue;
            }

            Deque<Integer> deque = new ArrayDeque<>();
            for (int j = 0; j < m; j++) {
                deque.add(arr[i][j]);
            }

            for (int j = 0; j < k; j++) {
                if (isClockwise(d)) {
                    int num = deque.pollLast();
                    deque.addFirst(num);
                } else {
                    int num = deque.pollFirst();
                    deque.addLast(num);
                }
            }

            for (int j = 0; j < m; j++) {
                arr[i][j] = deque.poll();
            }
        }
    }

    private void removeOrAdjust() {
        boolean[][] marked = new boolean[n][m];
        boolean isFound = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (arr[i][j] == 0) {
                    continue;
                }

                for (int k = 0; k < dx.length; k++) {
                    int nx = i + dx[k];
                    int ny = (j + dy[k] + m) % m;

                    if (nx >= 0 && nx < n && arr[i][j] == arr[nx][ny]) {
                        isFound = true;
                        marked[i][j] = true;
                        marked[nx][ny] = true;
                        break;
                    }
                }
            }
        }

        if (isFound) {
            remove(marked);
        } else {
            adjustByAverage();
        }
    }

    private void remove(boolean[][] marked) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (marked[i][j]) {
                    arr[i][j] = 0;
                }
            }
        }
    }

    private void adjustByAverage() {
        int sum = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += arr[i][j];
                if (arr[i][j] != 0) {
                    cnt++;
                }
            }
        }

        double avg = (double) sum / cnt;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    continue;
                }
                if (arr[i][j] > avg) {
                    arr[i][j]--;
                } else if (arr[i][j] < avg) {
                    arr[i][j]++;
                }
            }
        }
    }

    private boolean isClockwise(int d) {
        return d == 0;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main main = new Main();


        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            main.solution(x, d, k);
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer += arr[i][j];
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        br.close();
        bw.close();
    }
}