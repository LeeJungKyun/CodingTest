import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static ArrayList<Integer> num = new ArrayList<>();
	static ArrayList<Character> operation = new ArrayList<>();
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		String line = br.readLine();
		for(int i = 0; i < n; i++) {
			//숫자
			if(i % 2 == 0)
				num.add(line.charAt(i) - '0');
			//연산자
			else {
				operation.add(line.charAt(i));
			}
		}
		
		int start = num.get(0);
		dfs(0, start);
		System.out.println(max);
	}
	
	public static void dfs(int cur, int sum) {
		if(cur >= operation.size()) {
			max = Math.max(max,  sum);
			return;
		}
		
		//괄호 없는 경우
		int normalSum = calculate(cur, sum, num.get(cur + 1));
		dfs(cur + 1, normalSum);
		
		//괄호 있는 경우
		//연산자가 아직 남아있을 경우에만
		if(cur + 1 < operation.size()) {
			 //다음 연산자를 괄호로 묶어서 계산
			int bracketsSum = calculate(cur + 1, num.get(cur + 1), num.get(cur + 2));
			//이번 값과 다음 값 더한 값
			int result = calculate(cur, sum, bracketsSum);
			dfs(cur + 2, result);
		}
		
	}
	
	public static int calculate(int operationIdx, int x, int y) {
		switch(operation.get(operationIdx)) {
			case '+':
				return x + y;
			case '-':
				return x - y;
			case '*':
				return x * y;
		}
		return 1;
	}
}
