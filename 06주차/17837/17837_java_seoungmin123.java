import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int K;
    static int N;
    static int[][] chesspos;

    static List<Integer>[][] stack;

    static class Horse{
        int x, y;
        int dir;

        Horse(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static Horse[] horse;
    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        chesspos = new int[N][N];
        stack = new ArrayList[N][N];
        horse = new Horse[K+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                chesspos[i][j] = Integer.parseInt(st.nextToken());
                stack[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(st.nextToken()) -1;
            int y = Integer.parseInt(st.nextToken()) -1;
            int dir = Integer.parseInt(st.nextToken());

            horse[i] = new Horse(x, y, dir);
            stack[x][y].add(i);
        }

        System.out.println(simulation());

    }

    static int simulation(){
        int turn = 0;

        if (check()){
            return 0;
        }

        while(turn <= 1000){
            turn++;

            for (int i =1; i <= K; i++) {
                move(i);

                if(check()){
                    return turn;
                }
            }

        }
        return -1;
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(stack[i][j].size() >= 4){
                    return true;
                }
            }
        }
        return false;
    }

    static void move(int horseIdx) {
        Horse h = horse[horseIdx];

        int x = h.x;
        int y = h.y;
        int dir = h.dir;

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(nx < 0 || nx >= N || ny < 0 || ny >= N){
            blueMove(horseIdx);
            return;
        }

        int color = chesspos[nx][ny];

        switch (color){
            case 0 :
                whiteMove(horseIdx,nx,ny);
                break;
            case 1 :
                redMove(horseIdx,nx,ny);
                break;
            case 2 :
                blueMove(horseIdx);
                break;
        }
    }

    static void whiteMove(int horseIdx, int nx, int ny) {
        Horse h = horse[horseIdx];
        int x = h.x;
        int y = h.y;

        int idx = stack[x][y].indexOf(horseIdx);

        List<Integer> moving = new ArrayList<>();
        for(int i = idx; i < stack[x][y].size(); i++ ) {
            moving.add(stack[x][y].get(i));
        }

        if (stack[x][y].size() > idx) {
            stack[x][y].subList(idx, stack[x][y].size()).clear();
        }

        for(int num : moving){ //새로운 칸에 추가
            stack[nx][ny].add(num);
            horse[num].x = nx;
            horse[num].y = ny;
        }

    }

    static void redMove(int horseIdx, int nx, int ny) {
        Horse h = horse[horseIdx];
        int x = h.x;
        int y = h.y;

        int idx = stack[x][y].indexOf(horseIdx);

        List<Integer> moving = new ArrayList<>();
        for(int i = idx; i < stack[x][y].size(); i++ ) {
            moving.add(stack[x][y].get(i));
        }

        if (stack[x][y].size() > idx) {
            stack[x][y].subList(idx, stack[x][y].size()).clear();
        }

        // 순서를 뒤집어서 새로운 칸에 추가
        for (int i = moving.size() - 1; i >= 0; i--) {
            int num = moving.get(i);
            stack[nx][ny].add(num);
            horse[num].x = nx;
            horse[num].y = ny;
        }
    }

    static void blueMove(int horseIdx) {
        Horse h = horse[horseIdx];

        h.dir = reverseDir(h.dir);


        int nx = h.x + dx[h.dir];
        int ny = h.y + dy[h.dir];

        if(nx < 0 || nx >= N || ny < 0 || ny >= N){
            return;
        }

        int color = chesspos[nx][ny];
        if (color == 0) {
            whiteMove(horseIdx, nx, ny);
        } else if (color == 1) {
            redMove(horseIdx, nx, ny);
        }
    }

    private static int reverseDir(int dir) {
        if(dir == 1) return 2;
        else if(dir == 2) return 1;
        else if(dir == 3) return 4;
        else return 3;
    }
}
