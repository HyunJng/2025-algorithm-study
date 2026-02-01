package 17주차.14725;

import java.io.*;
import java.util.*;

class Trie{
    Map<String, Trie> child = new TreeMap<>();
    boolean end; 

    public void insert(String[] arr){
        Trie node = this;
        for(String s: arr){
            node = node.child.computeIfAbsent(s, k-> new Trie());
        }
        node.end = true;
    }
    public void dfs(int cnt){
        for(String key: child.keySet()){
            for(int i = 0; i<cnt; i++){
                System.out.print("--");
            }
            System.out.println(key);
            child.get(key).dfs(cnt+1);
        }
    }
}
public class 14725_java_Kyounglin {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Trie trie = new Trie();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            String[] arr = new String[k];
            for(int j = 0; j<k; j++){   
                arr[j] = st.nextToken();
            }
            trie.insert(arr);
        }
        trie.dfs(0);

    }

}
