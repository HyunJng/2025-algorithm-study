import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(simulation());
    }

    static int simulation(){
        int count = 0;

        for (int i = 0; i < N; i++) {
            if (validMap(map[i])){
                count++;
            }
        }

        for (int i = 0; i < N; i++) {
            int[] col = new int[N];
            for (int j = 0; j < N; j++) {
                col[j] = map[j][i];
            }
            if (validMap(col)){
                count++;
            }
        }
        return count;

    }

    static boolean validMap(int[] road){
        boolean[] valid = new boolean[N];

        for (int i = 0; i < N-1; i++) {
            int heightDifference = road[i] - road[i + 1];

            if(heightDifference == 0 ){
                continue;
            }

            if (Math.abs(heightDifference) > 1 ){
                return false;
            }

            if(heightDifference == 1){
                for (int j = i + 1; j <= i + L; j++) {
                    if (j>= N || road[j] != road[i+1] || valid[j]){
                        return false;
                    }
                    valid[j] = true;
                }
            }

            if(heightDifference == -1){
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || road[j] != road[i] || valid[j]){
                        return false;
                    }
                    valid[j] = true;
                }
            }
        }
        return true;
    }
}
