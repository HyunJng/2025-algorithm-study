import java.io.*;
import java.util.*;

public class 9935_java_Kyounglin {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String bomb = br.readLine();
        StringBuilder sb = new StringBuilder();
    
        for(char c: s.toCharArray()){
            sb.append(c);
            if(c==bomb.charAt(bomb.length()-1) && sb.length()>=bomb.length()){
                if(sb.substring(sb.length()-bomb.length()).equals(bomb)){
                    sb.delete(sb.length()-bomb.length(), sb.length());
                }
            }
        }
        System.out.println(sb.length()==0? "FRULA":sb.toString());
       
    }
   
    
}
