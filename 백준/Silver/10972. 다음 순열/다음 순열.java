/**
 * @author 이정균
 * 1부터 N까지의 수로 이루어진 순열이 있다. 이때, 사전순으로 다음에 오는 순열을 구하는 프로그램을 작성하시오.
 * N이 주어지고
 * 순열이 주어지면 다음 순열을 출력
 * 
 * 다음 순열을 구하는 방법
 * 	뒤에서 탐색해서 감소하는 지점을 찾음(A[i - 1] < A[i])
 * 	i - 1보다 큰 값을 j에서 찾음 (A[i - 1] < A[j] 인 가장 큰 J를 찾는다
 * 	i - 1과 j를 swap한 뒤, i부터 끝까지 오름차순 정렬
 * 1. N입력
 * 2. 순열 입력
 * 3. nextPermuation() 함수 실행
 * 	3-1. 뒤에서부터 탐색해서 감소하는 지점을 찾음
 * 	3-2. decreasingIndex가 0이라면 다음 순열 존재 X -> -1 출력 후 return
 *  3-3. decreasingIndex를 기준으로 왼쪽 파트의 가장 끝 숫자에 대해서 오른쪽 끝부터 더 큰 숫자를 찾는다.
 *  3-4. 찾은 두 수를 swap한다.
 *  3-5. 오른쪽 부분 정렬
 *  3-6. 출력
 */
import java.io.*;
import java.util.*;
public class Main {
	static int n;
	static int[] elementArr;
	static BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. N입력
		n = Integer.parseInt(br.readLine());
		
		//2. 순열 입력
		elementArr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			elementArr[i] = Integer.parseInt(st.nextToken());
		
		nextPermutation();
	}
	
	public static void nextPermutation() {
		int decreasingIndex = n - 1;
		
		//3-1. 뒤에서부터 탐색해서 감소하는 지점을 찾음
		while(decreasingIndex > 0 && elementArr[decreasingIndex] < elementArr[decreasingIndex - 1]) {
			decreasingIndex--;
		}
		
		//3-2. decreasingIndex가 0이라면 다음 순열 존재 X -> -1 출력 후 return
		if(decreasingIndex == 0) {
			System.out.println(-1);
			return;
		}

		
		//3-3. decreasingIndex를 기준으로 왼쪽 파트의 가장 끝 숫자에 대해서 오른쪽 끝부터 더 큰 숫자를 찾는다.
		int leftEnd = elementArr[decreasingIndex - 1];
		int rightIndex = n - 1;
		
		while(rightIndex > decreasingIndex && leftEnd >= elementArr[rightIndex]) {
			rightIndex--;
		}
		
		//3-4. 찾은 두 수를 swap한다.
		int temp = leftEnd;
		elementArr[decreasingIndex - 1] = elementArr[rightIndex];
		elementArr[rightIndex] = temp;
		
		//3-5. 오른쪽 부분을 정렬
		Arrays.sort(elementArr, decreasingIndex, n);
		
		//3-6. 출력
		for(int num : elementArr)
			System.out.print(num + " ");
		
	}
}
