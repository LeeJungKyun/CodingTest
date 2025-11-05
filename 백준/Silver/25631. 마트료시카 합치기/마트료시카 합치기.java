import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] arr;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num : arr)
        	map.put(num, map.getOrDefault(num, 0) + 1);
        
        int max = 0;
        int result = 0;
        
        for(int count : map.values()) {
        	if(count > max) {
        		max = count;
        		result = count;
        	}
        }
        System.out.println(result);
    }
}
