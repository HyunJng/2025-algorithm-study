import java.io.*;
import java.util.*;

class Tree implements Comparable<Tree>{
    int x;
    int y;
    int age;

    public Tree(int x, int y, int age){
        this.x = x;
        this.y = y;
        this.age = age;
    }

    @Override
    public int compareTo(Tree ob){
        return Integer.compare(this.age, ob.age);
    }
}
public class 16235_java_Kyounglin {
    static int n;
    static int m;
    static int k;
    static int[][] nutrient;
    static List<int[]> a = new ArrayList<>();
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static PriorityQueue<Tree> trees = new PriorityQueue<>();
    static Queue<Tree> deadTrees = new LinkedList<>();


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        nutrient = new int[n][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                int add = Integer.parseInt(st.nextToken());
                a.add(new int[]{i, j, add});
                
            }
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            trees.add(new Tree(x-1, y-1, age));
        }


        for(int i = 0; i<n; i++){
            Arrays.fill(nutrient[i], 5);
        }

        for(int i = 0; i<k; i++){
            spring();
            summer();
            fall();
            winter();
        }
        System.out.println(trees.size());

    }
    static void spring(){
        List<Tree> remainTrees = new ArrayList<>();
        while(!trees.isEmpty()){
            Tree tree = trees.poll();
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;

            if(nutrient[x][y]>= age){
                nutrient[x][y] -= age;
                remainTrees.add(new Tree(x, y, age+1));
            }else{
                deadTrees.add(new Tree(x, y, age));
            }
        }
        for(Tree remain: remainTrees){
            trees.add(remain);
        }
        
    }
    static void summer(){
        while(!deadTrees.isEmpty()){
            Tree deadTree = deadTrees.poll();
            nutrient[deadTree.x][deadTree.y] += deadTree.age/2;
        }
    }
    static void fall(){
        List<Tree> newTrees = new ArrayList<>();
        while(!trees.isEmpty()){
            Tree tree = trees.poll();
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;
            if(age%5==0){
                for(int i = 0; i<8; i++){
                    int nx = x+dx[i];
                    int ny = y+dy[i];
                    if(0<=nx && nx<n && 0<=ny && ny<n){
                        newTrees.add(new Tree(nx, ny, 1)); //새 나무
                    }
                }
            }
            newTrees.add(new Tree(x, y, age));    //원래 있던 나무
        }
        for(Tree newTree: newTrees){ //임시저장한 새 나무들 심기
            trees.add(newTree);
        }
    }
    static void winter(){
        for(int[] arr: a){
            int x = arr[0];
            int y = arr[1];
            int add = arr[2];

            nutrient[x][y] += add;
        }
    }
}
