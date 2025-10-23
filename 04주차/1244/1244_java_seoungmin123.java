import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int status[] ;
    static int student[] ;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bufferedReader.readLine());
        status= new int[N+1];

        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= N; i++) {
            status[i] = Integer.parseInt(st.nextToken());
        }

        int studentCount = Integer.parseInt(bufferedReader.readLine());
        student = new int[studentCount+1] ;

        for (int i = 0; i < studentCount; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            simulation(gender, num);

        }

        printStatus();

    }

    static void simulation(int gender, int num){
        if (gender == 1) {
            changeByBoy(num);
        }else {
            changeByGirl(num);
        }
    }

    static void changeByBoy(int num){ //배수 위치 반전
        for (int i = num; i <= N; i++) {
            if(i % num ==0){
                status[i] = status[i] == 1? 0: 1;
            }
        }
    }

    static void changeByGirl(int num){
        status[num] = status[num] == 1? 0: 1;

        int right = num + 1;
        int left = num - 1;

        while ((left >= 1 && right<=N) && status[left] == status[right]){
            status[left] = status[left] == 1? 0: 1;
            status[right] = status[right] == 1? 0: 1;
            left --;
            right ++;
        }
    }

    static void printStatus(){
        for (int i = 1; i <= N; i++) {
            System.out.print(status[i]);

            if(i % 20 == 0){
                System.out.println();
            }else if(i != N){
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
