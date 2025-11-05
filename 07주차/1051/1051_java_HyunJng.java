import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                arr[i][j] = temp[j];
            }
        }

        System.out.println(solution());
    }

    public static int solution() {
        int result = 0;

        for (int width = 1; width < Math.max(n, m); width++) {
            for (int i = 0; i + width < n; i++) {
                for (int j = 0; j + width< m; j++) {
                    if (arr[i][j] == arr[i][j + width]
                            && arr[i + width][j] == arr[i + width][j + width]
                            && arr[i][j + width] == arr[i + width][j]
                    ) {
                        result = width;
                    }
                }
            }
        }
        result++;
        return result * result;
    }
}