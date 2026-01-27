import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String explosionStr = br.readLine();

        bw.write(solution(str, explosionStr));

        bw.flush();
        br.close();
        bw.close();
    }

    public static String solution(String str, String explosionStr) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            if (sb.length() < explosionStr.length()) {
                continue;
            }

            boolean isSame = true;
            for (int j = 0; j < explosionStr.length(); j++) {
                if (sb.charAt(sb.length() - explosionStr.length() + j) != explosionStr.charAt(j)) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                sb.delete(sb.length() - explosionStr.length(), sb.length());
            }
        }

        if (sb.length() == 0) {
            return "FRULA";
        }

        return sb.toString();
    }

}