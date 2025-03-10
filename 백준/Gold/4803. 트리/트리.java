import java.util.*;
import java.io.*;

class Main{
    static int[] parent;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cnt = 1;

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n==0 && m==0) break;

            sb.append("Case "+(cnt++)+": ");
            parent = new int[n+1];
            for(int i=1;i<=n;i++) parent[i] = i;

            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a,b);
            }

            Set<Integer> tree = new HashSet<>();
            for(int i=1;i<=n;i++){
                if(find(parent[i])>0) tree.add(find(parent[i]));
            }

            if(tree.size()==0) sb.append("No trees.");
            else if(tree.size()==1) sb.append("There is one tree.");
            else sb.append("A forest of "+tree.size()+" trees.");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a==b){
            parent[b] = a;
            parent[a] = 0;
        }
        else if(a<b) parent[b] = a;
        else parent[a] = b;
    }

    static int find(int a){
        if(a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }
}