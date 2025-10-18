import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int T;
    static int[][] board;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            simulation(x, d, k);
        }
        System.out.println(calculate());
    }

    static int calculate(){
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum += board[i][j];
            }
        }

        return sum;
    }

    static void simulation(int x, int d, int k){
        //회전
        rotate(x, d, k);

        //인접한 수 제거
        if(!removeNum()){
            average();
        }
    }

    static void rotate(int x, int d, int k){
        k = k % M;
        if(k == 0)return;

        for (int i = x-1; i < N; i+=x) {
            int[] temp = new int[M];

            for (int j = 0; j < M; j++) {
                if(d == 0){ // 시계 방향 k
                    temp[(j+k)%M] = board[i][j];
                }else { // 반시계 방향 k
                    temp[(j-k+M)%M] = board[i][j];
                }
            }
            board[i] = temp;
        }
    }

    static boolean removeNum(){
        boolean removeYN = false;
        boolean toRemove[][] = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j] == 0) continue;

                int current = board[i][j];

                //좌우
                int left = (j-1+M)%M;
                int right = (j+1)%M;

                if(current == board[i][left]){
                    toRemove[i][j] = true;
                    toRemove[i][left] = true;
                    removeYN = true;
                }

                if(current == board[i][right]){
                    toRemove[i][j] = true;
                    toRemove[i][right] = true;
                    removeYN = true;
                }

                //아래
                if( i > 0 && current ==board[i-1][j]){
                    toRemove[i][j] = true;
                    toRemove[i-1][j] = true;
                    removeYN = true;
                }

                //위
                if( i < N-1 && current ==board[i+1][j]){
                    toRemove[i][j] = true;
                    toRemove[i+1][j] = true;
                    removeYN = true;
                }
            }
        }

        if (removeYN){
            realRomove(toRemove);
        }
        return removeYN;
    }

    static void realRomove(boolean[][] toRemove){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (toRemove[i][j]){
                    board[i][j] = 0;
                }
            }
        }
    }

    static void average(){
        int sum = 0;
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j] != 0){
                    sum += board[i][j];
                    count++;
                }
            }
        }

        double avg = (double) sum / count;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j] > 0){
                    if(board[i][j] > avg){
                        board[i][j]--;
                    }else if(board[i][j] < avg){
                        board[i][j]++;
                    }
                }
            }
        }
    }
}
