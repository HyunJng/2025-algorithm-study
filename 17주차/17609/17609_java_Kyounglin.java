import java.io.*;
import java.util.*;

public class 17609_java_Kyounglin{


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i<n; i++){
            String s = br.readLine();
            int lt = 0;
            int rt = s.length()-1;
            int pass = 0;
            while(lt<rt){
                if(s.charAt(lt)==s.charAt(rt)){
                    lt++;
                    rt--;
                    
                }else{
                    
                    boolean right = check(lt+1, rt, s);
                    boolean left = check(lt, rt-1, s);
                    if(left || right){
                        pass = 1;
                    }else{
                        pass = 2;
                    }
                    break;
                }

            }
            System.out.println(pass);
        
        }   
    }
    static boolean check(int lt, int rt, String s){
        while(lt<rt){
            if(s.charAt(lt)==s.charAt(rt)){
                    lt++;
                    rt--;
            }else{
                return false;
            }
        }
        return true;
    }
   
    
}
