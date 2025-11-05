import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, L;
    static int[][] map;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //가로 확인
        for (int i = 0; i < N; i++) {
            boolean isAvailable = true;
            boolean[] visited = new boolean[N];

            int idx = 0;
            while (idx <= N - 2 && isAvailable) {

                //경사로 설치하지 않아도 됨.
                if (map[i][idx] == map[i][idx + 1]) {
                    idx++;
                    continue;
                }

                //경사로 설치 경우
                //1. 높이 차가 1보다 크다면, 설치 불가
                if (Math.abs(map[i][idx] - map[i][idx + 1]) > 1) {

                    isAvailable = false;
                    break;
                }

                //2. 높이 차가 1이라면 설치 가능 여부 확인 후 설치
                loop1:
                if (idx + 1 < N && map[i][idx] < map[i][idx + 1]) { //만약 오름차순이라면 len 만큼 이전에

                    for (int len = 0; len < L; len++) {
                        if (idx - len < 0 || map[i][idx - len] != map[i][idx] || visited[idx - len]) {
                            isAvailable = false;
                            break loop1;
                        }
                    }
                    idx = idx + 1;
                }

                //2. 만약 내림차순이라면 len 만큼 같은지
                if (idx + 1 < N && map[i][idx] > map[i][idx + 1]) {
                    visited[idx + 1] = true;
                    for (int len = 0; len < L; len++) {
                        if (idx + 1 + len >= N || map[i][idx + 1 + len] != map[i][idx + 1]) {
                            isAvailable = false;
                            break;
                        }
                        visited[idx + 1 + len] = true;
                    }
                    idx = idx + L;
                }
            }
            if (isAvailable) {
                answer++;
            }
        }

        //세로 확인
        for (int j = 0; j < N; j++) {
            boolean isAvailable = true;
            boolean[] visited = new boolean[N];

            int idx = 0;
            while (idx <= N - 2 && isAvailable) {
                //경사로 설치하지 않아도 됨.
                if (map[idx][j] == map[idx + 1][j]) {
                    idx++;
                    continue;
                }

                //경사로 설치 경우
                //1. 높이 차가 1보다 크다면, 설치 불가
                if (Math.abs(map[idx][j] - map[idx + 1][j]) > 1) {

                    isAvailable = false;
                    break;
                }

                //2. 높이 차가 1이라면 설치 가능 여부 확인 후 설치
                loop1:
                if (idx + 1 < N && map[idx][j] < map[idx + 1][j]) { //만약 오름차순이라면 len 만큼 이전에

                    for (int len = 0; len < L; len++) {
                        if (idx - len < 0 || map[idx - len][j] != map[idx][j] || visited[idx - len]) {
                            isAvailable = false;
                            break loop1;
                        }
                    }
                    idx = idx + 1;
                }

                //2. 만약 내림차순이라면 len 만큼 같은지
                if (idx + 1 < N && map[idx][j] > map[idx + 1][j]) {
                    visited[idx + 1] = true;
                    for (int len = 0; len < L; len++) {
                        if (idx + 1 + len >= N || map[idx + 1 + len][j] != map[idx + 1][j]) {
                            isAvailable = false;
                            break;
                        }
                        visited[idx + 1 + len] = true;
                    }
                    idx = idx + L;
                }
            }
            if (isAvailable) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}