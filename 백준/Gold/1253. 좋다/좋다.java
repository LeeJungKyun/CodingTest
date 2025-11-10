import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] arr;
	static HashSet<Integer> set;
	static HashMap<Integer, Integer> map;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. n 입력
		n = Integer.parseInt(br.readLine());
	
		//2. 배열 입력 (원소 <= 10억)
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		map = new HashMap<>();
        
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}
		
		set = new HashSet<>();
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				int sum = arr[i] + arr[j];

                if (arr[i] == 0 && arr[j] == 0) {
                    if (map.getOrDefault(sum, 0) >= 3) {
                        set.add(sum);
                    }
                } else if (arr[i] == 0 || arr[j] == 0) {
                    if (map.getOrDefault(sum, 0) >= 2) {
                        set.add(sum);
                    }

                } else {
                    set.add(sum);
                }
			}
		}
		
		int count = 0;
		for(int num : arr) {
			if(set.contains(num))
				count++;
		}
		System.out.println(count);
	}
}