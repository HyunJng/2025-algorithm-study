import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[][]borad;

    static int[][][] tetrominos = {
            // I 모양
            {{0,0},{0,1},{0,2},{0,3}},
            {{0,0},{1,0},{2,0},{3,0}},

            // O 모양
            {{0,0}, {0,1}, {1,0}, {1,1}},

            // L 모양
            {{0,0}, {1,0}, {2,0}, {2,1}},
            {{0,0}, {0,1}, {1,0}, {2,0}},
            {{0,0}, {0,1}, {0,2}, {1,2}},
            {{0,0}, {0,1}, {0,2}, {1,0}},
            {{0,0}, {1,0}, {1,1}, {1,2}},
            {{0,0}, {0,1}, {1,1}, {2,1}},
            {{0,2}, {1,0}, {1,1}, {1,2}},
            {{0,0}, {1,0}, {2,0}, {2,-1}},

            // Z 모양
            {{0,0}, {1,0}, {1,1}, {2,1}},
            {{0,1}, {1,0}, {1,1}, {2,0}},
            {{0,0}, {0,1}, {1,1}, {1,2}},
            {{0,1}, {0,2}, {1,0}, {1,1}},

            // T 모양
            {{0,0}, {0,1}, {0,2}, {1,1}},
            {{0,0}, {1,0}, {1,1}, {2,0}},
            {{0,1}, {1,0}, {1,1}, {1,2}},
            {{0,1}, {1,0}, {1,1}, {2,1}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        borad = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                borad[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(simulation());
    }

    static int simulation(){
        int maxSum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int[][] tetromino : tetrominos){
                    int sum = 0;
                    boolean valid = true;

                    for (int[] pos : tetromino){
                        int ny = i + pos[0];
                        int nx = j + pos[1];

                        if (ny >=0 && ny < N && nx >= 0 && nx < M){
                            sum += borad[ny][nx];
                        }else {
                            valid = false;
                            break;
                        }
                    }

                    if(valid){
                        maxSum = Math.max(maxSum, sum);
                    }
                }
            }
        }
        return maxSum;
    }
}
