import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * KMP 알고리즘을 사용하는 문제
 * 기존 문자열의 길이 : N
 * 찾는 문자열의 길이 : M
 * 기존의 2중 for문을 사용하는 방식은 O(N x M)너무 오래걸리기 때문에 "실패 지점"을 찾는 함수를 통해서 O(N + M) 에 해결할 수 있도록 하는 알고리즘
 * 
 * "실패 지점을 찾는 함수"란?
 * 실패함수 F(x) 는 문자열 [0 : x + 1] 에서 자기 자신을 제외하고 접두사와 접미사가 일치하는 최대 길이
 * 
 * 만약에 기존 문자열이 ABABDABACDABABC 이고, 내가 찾는 문자열은 ABABC 라고 해보자.
 * 그러면 당연히 0번 인덱스부터 비교하다가 D와 C가 다르다는 것을 발견을 하겠죠
 * 기존 문자열 : A B A B *D* A B A ...
 * 찾는 문자열 : A B A B *C*
 * 
 * 기존의 2중 for문 구조에서는 그러면 다음 차례 때 이렇게 구분을 할 텐데 이럴 필요가 없어요 KMP 알고리즘이 있으니깐
 * 기존 문자열 : A B A B D A B A ...
 * 찾는 문자열 :   A B A B C
 * 
 * 다시 처음으로 돌아가서 D와 C가 다르다는 것을 눈치 챈 시점으로 돌아갑시다
 * 우리는 직관적으로 봤을 때 "이거 그냥 2번 인덱스에 있는 A B 부터 탐색해도 될 것 같은데?" 라고 생각하고 그것을 코드로 구현한게 KMP 입니다.
 * 
 * 하지만 이건 직관이고 이걸 실제로 코드로 구현할 때 "실패 함수" 를 통해서,
 * 패턴의 특정 위치까지 일치했다가 실패 했을 때, 어디로 돌아가서 탐색을 하는게 가장 효율적인가를 알려준다
 * 
 * 이제 아까 예제로 돌아가서 생각을 해보자
 * 
 * 실패 함수의 계싼은 가장 긴 접두사이면서 동시에 접미사인 부분 문자열의 길이를 찾는 과정이다.
 * 각 위치마다 패턴의 앞부분(접두사)와 뒷부분(접미사)이 얼마나 겹치는지 계산을 하면 된다.
 * 
 * 인덱스		패턴문자	부분문자열		접두사/접미사 일치 길이
 *   0		  A		 A					0
 *   1		  B		 AB					0
 *   2		  A		 ABA				1 (앞의 A, 뒤의 A)
 *   3		  B      ABAB				2 (앞의 AB, 뒤의 AB)
 *   4		  C		 ABABC				0 (일치하는 부분 X)
 *   
 * 이렇게 되면 실패 함수 배열은 [ 0, 0, 1, 2, 0 ] 이 된다.
 * 이걸 왜 구했냐?
 * 아까 아래와 같이 실패지점이 5번째 문자에서 발생했다.
 * 기존 문자열 : A B A B *D* A B A ...
 * 찾는 문자열 : A B A B *C*
 * 
 * 이 때 우리는 패턴의 불일치가 발생하기 직전까지 일치했던 부분의 길이를 알아야 한다.
 * 그러면 우리는 패턴의 4번째 문자(3번 인덱스)인 'B'까지 일치했다는 것을 알고 있다.(ABAB 까지는 일치했다)
 * 
 * 그래서 KMP 알고리즘은 불일치가 발생한 위치(5번째)의 이전 위치 4번째인 3번 인덱스에 해당하는 실패 함수 값을 참고한다.
 * 이 값은 불일치가 발생하기 전까지 일치했던 부분에서 가장 긴 접두사이면서 동시에 접미사인 부분의 길이를 알려주는 것이다.
 * 이 값의 뜻은 패턴의 2번째 인덱스부터 비교해라 라는 건데, 그게 가능한 이유는 일치했던 부분의 접미사가 패턴의 접두사와 일치하기 때문이다.
 * 
 * 이 값을 이용해서, KMP 알고리즘은 패턴의 2번째 인덱스인 'A' 부터 비교를 시작하면 된다.
 *  
 *  
 * 1. 기존 문자열을 입력받기
 * 2. 내가 찾을 패턴을 입력받기
 * 3. 패턴의 접미사와 접두사의 일치 길이를 저장하는 lps 배열을 만들기
 */
public class Main {
	static String str, pattern;
	static int strLength, patternLength;
	static int[] longestPrefixSameWithSuffix;
	static int patternFoundCount = 0;
	static ArrayList<Integer> patternFoundIndexList;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 기존 문자열을 입력받기
		str = br.readLine();
		strLength = str.length();
		
		//2. 내가 찾을 패턴을 입력받기
		pattern = br.readLine();
		patternLength = pattern.length();
		
		//3. 패턴의 접미사와 접두사의 일치 길이를 저장하는 lps 배열을 만들기
		longestPrefixSameWithSuffix = new int[patternLength];
		int patternIndex = 0;
		for(int  i = 1; i < patternLength; i++) {
			while (patternIndex > 0 && pattern.charAt(i) != pattern.charAt(patternIndex)) {
	            patternIndex = longestPrefixSameWithSuffix[patternIndex - 1];
	        }
			if (pattern.charAt(i) == pattern.charAt(patternIndex)) {
				patternIndex++;
	        }
			longestPrefixSameWithSuffix[i] = patternIndex;
		}
		
		patternFoundIndexList = new ArrayList<>();
		KMPSearch(str, pattern);
		
		System.out.println(patternFoundCount);
		for(int foundPatternIndex : patternFoundIndexList)
			System.out.print(foundPatternIndex + " ");
	}
	
	public static void KMPSearch(String text, String pattern) {
		int textIndex = 0;
		int patternIndex = 0;
		
		while(textIndex < strLength) {
			//패턴과 문자열이 똑같이 생겼으면 계속 이동하면 됨
			if(pattern.charAt(patternIndex) == text.charAt(textIndex)) {
				textIndex++;
				patternIndex++;
			}
			
			//만약에 패턴의 인덱스와 패턴의 길이가 같아졌으면 찾은거니깐 더해주기
			if(patternIndex == patternLength) {
				patternFoundIndexList.add(textIndex - patternIndex + 1);
				patternFoundCount++;
				//다음 패턴을 찾아줘야하니깐 patternIndex를 업데이트 해주기
				//여기서 patternIndex - 1의 실패지점을 가져오는 이유는 패턴을 찾은 후에도 혹시 그 뒤에 바로 이어지는 패턴의 일부가 있을 수도 있다.
				//예를 들어서 ABABCABAB에서 ABAB를 찾는다고 하면
				//실패 배열은 0, 0, 1, 2 가 될테고
				//처음 ABAB를 찾고 나면 patternIndex는 4일텐데 거기서 lps[3] 값을 가져와서 2번 인덱스부터 다시 봐줘야 한다. 
				patternIndex = longestPrefixSameWithSuffix[patternIndex - 1];
			}
			else if (textIndex < strLength && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
				//여기선 불일치가 발생하기 전까지 몇개는 일치했다는 거니깐 배제하고 넘어갈 수가 없음
				//일치했던 부분에서 lps를 구해서 해당 값을 새로운 patternIndex로 사용
				if(patternIndex != 0)
					patternIndex = longestPrefixSameWithSuffix[patternIndex - 1];
				else
					textIndex++;
			}
		}
	}
}