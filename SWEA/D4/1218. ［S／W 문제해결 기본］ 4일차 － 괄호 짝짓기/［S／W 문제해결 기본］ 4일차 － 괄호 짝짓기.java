/**
 * @author 이정균
 * 4종류의 괄호문자들로 이루어진 문자열이 주어진다.
 * 문자열에 사용된 괄호들의 짝이 모두 맞는지 판별
 * 
 * 1. 10개의 테스트케이스 반복문
 * 2. 각 테스트 케이스 별 문자열의 길이 입력
 * 3. 문자열 입력
 * 4. 문자열에 대해서 isValidParenthesis() 함수 실행
 * 5. Stack 4개 선언
 * 6. 문자열의 각 인덱스에 대해 여는 괄호면 스택에 넣기
 * 7. 닫는 괄호라면 stack이 비었다면 false를 return
 * 8. stack이 비어있지 않다면 pop하기
 */
import java.io.*;
import java.util.*;

public class Solution {
	static final int TEST_CASE = 10;
	static int length;
	static String input;
	static boolean result;
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	public static void main(String[] args) throws IOException {
		//1. 10개의 테스트케이스 반복문
		for(int tc = 1; tc <= TEST_CASE; tc++) {
			//2. 각 테스트케이스 별 문자열의 길이 입력
			length = Integer.parseInt(br.readLine());
			//3. 문자열 입력
			input = br.readLine();
			result = isValidParenthesis();
			sb.append("#").append(tc).append(" ").append(result ? 1 : 0).append('\n');
		}
		System.out.println(sb);
	}
	
	
	public static boolean isValidParenthesis() {
		//5. Stack 4개 선언
		Stack<Character> stack1 = new Stack<>();
		Stack<Character> stack2 = new Stack<>();
		Stack<Character> stack3 = new Stack<>();
		Stack<Character> stack4 = new Stack<>();

		for(int i = 0; i < input.length(); i++) {
			//6. 문자열의 각 인덱스에 대해 여는 괄호면 스택에 넣기
			if(input.charAt(i) == '(') {
				stack1.push('(');
			} else if(input.charAt(i) == ')') {
				//7. 닫는 괄호라면 stack이 비었다면 false를 return
				if(stack1.isEmpty())
					return false;
				//8. stack이 비어있지 않다면 pop하기
				else stack1.pop();
			}
			else if(input.charAt(i) == '[') {
				stack2.push('[');
			} else if(input.charAt(i) == ']') {
				if(stack2.isEmpty())
					return false;
				else stack2.pop();
			}
			else if(input.charAt(i) == '{') {
				stack3.push('{');
			} else if(input.charAt(i) == '}') {
				if(stack3.isEmpty())
					return false;
				else stack3.pop();
			}
			else if(input.charAt(i) == '<') {
				stack4.push('<');
			} else if(input.charAt(i) == '>') {
				if(stack4.isEmpty())
					return false;
				else stack4.pop();
			}
		}
		
		if(stack1.isEmpty() && stack2.isEmpty() && stack3.isEmpty() && stack4.isEmpty())
			return true;
		return false;
	}
}
