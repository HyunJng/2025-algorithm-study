import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static char[] A_ARR;
    static int B;
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        String A = st.nextToken();
        B = Integer.parseInt(st.nextToken());

        A_ARR = A.toCharArray();
        visited = new boolean[A_ARR.length];
        solution("",0);

        System.out.println(answer);

    }

    private static void solution(String str, int depth){
        if(depth == A_ARR.length){
            int num = Integer.parseInt(str);
            if(num <= B){
                answer = Math.max(answer,num);
            }
            return ;
        }

        for (int i = 0; i < A_ARR.length; i++) {
            if(visited[i]) continue;
            if (depth == 0 && A_ARR[i] == '0') continue;

            visited[i] = true;
            solution(str + A_ARR[i],depth+1);
            visited[i] = false;
        }
    }
}
