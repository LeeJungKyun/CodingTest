import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * 뚜껑은 시계방향으로 회전 -> 번호도 같이 회전
 * 자물쇠의 비밀번호: 보물상자에 적힌 숫자로 만들 수 있는 모든 수 중, K번째로 큰 수를 10진수로 만든 수
 * N개의 숫자가 입력으로 주어졌을 때, 보물상자의 비밀번호를 출력
 *
 * 1. 테스트케이스 입력
 * 2. N, K입력
 * 3. N개의 숫자가 들어올텐데 이걸 4개로 나눠서 파싱해서 Arraydeque<String>에 저장
 * 		deque[0] 1 B 3
 * 		deque[1] B 3 B
 * 		deque[2] 8 1 F
 * 		deque[3] 7 5 E
 * 4. 회전은 문자열의 길이만큼 반복
 * 5. 현재 각 덱의 char들을 String으로 병합 후 TreeSet에 저장
 * 6. 다 저장했으면 시계방향 회전 -> 마지막꺼 뽑아서 첫번째 넣기 반복
 * 7. TreeSet을 ArrayList로 바꾸고 뒤에서 10번째꺼 가져오기
 * 
 */
public class Solution {
	static int testCase;
	static int n, k;
	static int password;
	static ArrayDeque<Character>[] deque;
	static TreeSet<String> passwordSet;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 테스트 케이스 입력
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			//2. N, K입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			//3. N개의 숫자가 들어올텐데 이걸 4개로 나눠서 파싱해서 Arraydeque<String>에 저장
			String input = br.readLine();
			int length = input.length() / 4;
			deque = new ArrayDeque[4];
			for(int i = 0; i < 4; i++)
				deque[i] = new ArrayDeque<>();
			
			for(int i = 1; i <= 4; i++) {
				String current = input.substring(length * (i - 1), length * i);
				for(int j = 0; j < current.length() ; j++) {
					deque[i - 1].add(current.charAt(j));
				}
			}
			
			passwordSet = new TreeSet<>();
			//4. 회전은 문자열의 길이만큼 반복
			for(int rotate = 0; rotate < length; rotate++) {
				//5. 현재 각 덱의 char들을 String으로 병합 후 TreeSet에 저장
				for(int i = 0; i < 4; i++) {
					String possiblePwd = "";
					for(char ch : deque[i])
						possiblePwd += ch;
					passwordSet.add(possiblePwd);
				}
				
				//6. 다 저장했으면 시계방향 회전 -> 마지막꺼 뽑아서 첫번째 넣기 반복 3번 인덱스의 마지막꺼는 미리 넣어두기
				char tempLast = deque[3].pollLast();
				for(int i = 3; i > 0; i--) {
					 deque[i].addFirst(deque[i-1].pollLast());
				}
				deque[0].addFirst(tempLast);
			}
			
			//7. TreeSet을 ArrayList로 바꾸고 뒤에서 10번째꺼 가져오기
			ArrayList<String> list = new ArrayList<>(passwordSet);
			
			String hexPassword = list.get(list.size() - k);
			int password = Integer.parseInt(hexPassword, 16);
			
			sb.append('#').append(tc).append(' ').append(password).append('\n');
		}
		
		System.out.println(sb);
	}
}