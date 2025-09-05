import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 투명한 막을 D장 쌓아서 제작한 보호 필름
 * 각 막은 특성을 가진다 (A = 0, B = 1)
 * 특정 막에 약품을 넣으면 해당 막은 모두 특성이 바뀐다
 * 
 * 충격을 견딜 수 있는 보호필름의 조건
 * - 단면의 모든 세로 방향에 대해서 동일한 특성의 셀들이 합격 기준인 K개 이상 연속적으로 있는 경우
 * 
 * - main
 * 1. 각 테스트케이스 별로 막의 높이 height와 너비 width, 통과 조건 K를 입력
 * 2. 필름 정보를 입력받기
 * 3. 최소 횟수를 찾기 위해서 findMinimum함수 실행 @param row, count 해서 sb에 넣기
 * 
 * - findMinimum 함수
 * 1. isValidFilm을 통해 가능하면 count를 리턴
 * 2. 현재 들어온 필름을 그대로 보낸 경우, A로 바꾼 경우, B로 바꾼 경우 하면서 다시 findMinimum으로 넘기고 원복 시키기
 * 
 * - isValidFilm 함수 
 * 1. 각 행 별로 모든 열을 탐색하면서 뭐든 그냥 연속된거 있으면 된다.
 * 2. maxStreak 으로 계산
 * 3. 그냥 전꺼랑 같으면 maxSteak늘리고 아니면 currentStreak = 1 초기화
 */

public class Solution {
	//상수 선언부
	static final int A = 0;
	static final int B = 1;
	
	//변수 선언부
	static int testCase;
	static int height, width, k;
	static int[][] film;
	static int minChange = Integer.MAX_VALUE;
	
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			minChange = Integer.MAX_VALUE;
			//1. 각 테스트케이스 별로 막의 높이 height와 너비 width, 통과 조건 K를 입력
			st = new StringTokenizer(br.readLine());
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			//2. 필름 정보를 입력받기
			film = new int[height][width];
			for(int i = 0; i < height; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < width; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			findMinimum(0, 0);
			
			sb.append('#').append(tc).append(' ').append(minChange).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void findMinimum(int row, int count) {
		if(count >= minChange) return;
		if(row == height) {
			if(isValidFilm()) {
				minChange = Math.min(minChange, count);
			}
			return;
		}
		
		
		//3. 현재 들어온 필름을 그대로 보낸 경우, A로 바꾼 경우, B로 바꾼 경우 하면서 다시 findMinimum으로 넘기고 원복 시키기
		int[] prevFilm = film[row].clone();
		
		//그대로 보낸 경우
		findMinimum(row + 1, count);
		
		//A로 바꾼 경우
		Arrays.fill(film[row], A);
		findMinimum(row + 1, count + 1);

		//B로 바꾼 경우
		Arrays.fill(film[row], B);
		findMinimum(row + 1, count + 1);
		
		//원복
		film[row] = prevFilm;
	}
	
	//1. 각 행 별로 모든 열을 탐색하면서 뭐든 그냥 연속된거 있으면 된다.
	public static boolean isValidFilm() {
		//2. maxStreak 으로 계산
	    for (int col = 0; col < width; col++) {
	        int maxStreak = 1;
	        int currentStreak = 1;

	        //3. 그냥 전꺼랑 같으면 maxSteak늘리고 아니면 currentStreak = 1 초기화
	        for (int row = 1; row < height; row++) {
	            if (film[row][col] == film[row - 1][col]) {
	                currentStreak++;
	            } else {
	                currentStreak = 1;
	            }

	            if (currentStreak > maxStreak) {
	                maxStreak = currentStreak;
	            }

	            if (maxStreak >= k) break;
	        }

	        if (maxStreak < k) {
	            return false; // 이 열은 통과하지 못했으므로 전체 필름 불합격
	        }
	    }
	    return true; // 모든 열이 통과
	}
}