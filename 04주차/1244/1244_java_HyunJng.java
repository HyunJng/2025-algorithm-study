import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    static final int WRITE_LEN = 20;
    static int length, studentsNum;
    static Boolean[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        length = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).map(i -> i.equals("1")).toArray(Boolean[]::new);
        studentsNum = Integer.parseInt(br.readLine());

        while (studentsNum-- > 0) {
            String[] student = br.readLine().split(" ");
            int number = Integer.parseInt(student[1]);

            switch (student[0]) {
                case "1": {
                    for (int i = number - 1; i < arr.length; i += number) {
                        arr[i] = !arr[i];
                    }
                }
                break;
                case "2": {
                    number -= 1;
                    arr[number] = !arr[number];
                    for (int find = 1; number - find >= 0 && number + find < arr.length; find++) {
                        if (arr[number - find] != arr[number + find]) break;
                        arr[number - find] = !arr[number - find];
                        arr[number + find] = !arr[number + find];
                    }
                }
            }
        }

        for (int i = 0; i < arr.length; i+= WRITE_LEN) {
            System.out.println(
                    Arrays.stream(Arrays.copyOfRange(arr, i, Math.min(arr.length, i + WRITE_LEN))).map(k -> k ? "1" : "0").collect(Collectors.joining(" "))
            );
        }
    }
}