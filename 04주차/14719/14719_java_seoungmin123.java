import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int width;
    static int hight;
    static int[] rains;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        hight = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        rains = new int[width];
        st = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < width; i++) {
            rains[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(simulation());
    }

    static int simulation(){
        int result = 0;

        int leftMax[] = new int[width];
        int rightMax[] = new int[width];

        leftMax[0] = rains[0];
        for (int i = 1; i < width; i++) {
            leftMax[i] = Math.max(leftMax[i-1], rains[i]);
        }

        rightMax[width-1] = rains[width-1];
        for (int i = width-2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], rains[i]);
        }

        for (int i = 0; i < width; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - rains[i];
        }

        return result;
    }
}
