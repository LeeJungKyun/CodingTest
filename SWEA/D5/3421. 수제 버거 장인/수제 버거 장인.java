/**
 * @author 이정균
 * 1 ~ N번까지의 재료
 * i번 재료와 j번 재료가 궁합이 안맞으면 만들 수 없음
 * 궁합이 안맞는 M개 쌍이 주어짐
 * 만들 수 있는 버거의 종류 구하기
 * 
 * 1. 테스트 케이스 입력받기
 * 2. 테스트 케이스 별 N, M 입력 받기
 * 3. M개 줄에 궁합이 맞지 않는 두 정수 입력받아서 impossiblePairs에 체크하기
 * 4. makeSubset 함수 실행
 *  4-1. isValid() 함수 실행 후 가능하면 count++
 *  	4-1-1. 선택여부와 가능한 조합인지 확인
 *  4-2. 시작인덱스부터 n이하까지 반복문
 *  4-3. 선택 체크
 *  4-4. 4-4. makeSubset에 i + 1 파라미터
 *  4-5. 선택 체크 해제
 * 5. StringBuilder에 출력 담기
 * 6. count 0으로 초기화
 * 7. 출력
 */
import java.io.*;
import java.util.*;
public class Solution {
	static int testCase;
	static int n, m;
	static boolean[][] impossiblePairs;
	static boolean[] isSelected;
	static int count = 0;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//1. 테스트 케이스 입력받기
		testCase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			//테스트 케이스 별 N, M 입력 받기
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			//3. M개 줄에 궁합이 맞지 않는 두 정수 입력받아서 possiblePairs에 체크하기
			impossiblePairs = new boolean[n + 1][n + 1];

			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int firstIngredient = Integer.parseInt(st.nextToken());
				int secondIngredient = Integer.parseInt(st.nextToken());
				impossiblePairs[firstIngredient][secondIngredient] = true;
				impossiblePairs[secondIngredient][firstIngredient] = true;
			}
			
			isSelected = new boolean[n + 1];
			//4. makeSubset 함수 실행
			makeSubset(1);
			
			//5. StringBuilder에 출력 담기
			sb.append("#").append(tc).append(" ").append(count).append('\n');
			//6. count 0으로 초기화
			count = 0;
		}
		//7. 출력
		System.out.println(sb);
	}
	
	public static void makeSubset(int startIndex) {
		//4-1. isValid 함수 실행 후 가능하면 count++
		if(isValid()) {
			count++;
		}
		//4-2. 시작인덱스부터 n이하까지 반복문
		for(int i = startIndex; i <= n; i++) {
			//4-3. 선택 체크
			isSelected[i] = true;
			//4-4. makeSubset에 i + 1 파라미터
			makeSubset(i + 1);
			//4-5. 선택 체크 해제
			isSelected[i] = false;
		}
	}
	
	//4-1-1. 선택여부와 가능한 조합인지 확인
	public static boolean isValid() {
		 for (int i = 1; i <= n; i++) {
	            if (!isSelected[i]) continue;
	            for (int j = i + 1; j <= n; j++) {
	                if (isSelected[j] && impossiblePairs[i][j]) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	}

