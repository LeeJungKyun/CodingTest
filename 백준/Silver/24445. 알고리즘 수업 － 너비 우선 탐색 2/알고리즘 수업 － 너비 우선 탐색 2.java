import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    static boolean visited[];
    static int N,M,R;
    
    public static void bfs(int r, LinkedList<Integer>[] list, boolean visited[])
    {
    	int order[] = new int[N+1];
    	Queue<Integer>q = new LinkedList<Integer>();
    	visited[r] = true;
    	q.add(r);
    	int count=1;
    	
    	while(q.size()!=0)
    	{
    		r = q.poll();
    		order[r] = count++;
    		
    		Iterator<Integer> iter = list[r].listIterator();
    		
    		while(iter.hasNext()) {
    			int w = iter.next();
    			if(!visited[w]) {
    				visited[w] = true;
    				q.add(w);
    			}
    		}
    	}
    	for(int i=1; i<=N; i++)
    	{
    		System.out.println(order[i]);
    	}
    	
    	
    }
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        LinkedList<Integer>[] list = new LinkedList[N+1];
        visited = new boolean[N+1];
        
        
        for(int i=0; i<=N; i++)
        {
        	list[i] = new LinkedList<Integer>();
        }
        
        for(int i=0; i<M; i++)
        {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	list[x].add(y);
        	list[y].add(x);
        }
        
        for(int i=1; i<=N; i++)
        {
        	Collections.sort(list[i], Collections.reverseOrder());
        }
        
        bfs(R,list,visited);

    }
}