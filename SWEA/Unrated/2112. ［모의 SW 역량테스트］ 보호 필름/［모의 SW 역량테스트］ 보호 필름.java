import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 1. 높이 d, 너비 w, 합격기준 k 입력
 * 2. film 배열 입력
 * 3. k == 1이면 무조건 통과
 * 4. 2중 for문을 돌리는데 첫번째 반복변수는 A를 바꾸는 경우의 수를 비트로 표현, 두번째 반복변수는 B를 바꾸는 경우의 수를 비트로 표현
 * 	4-1. 만약에 둘이 & 연산한게 0이 아니면 겹치는 걸 바꾸는 거기 때문에 세지 않는다.
 * 	4-2. 그렇게 해서 변환 횟수가 최소 변환 횟수보다 작다면 갱신하고 for문을 이어나가고 아니면 continue
 *  4-3. 복사본을 만들기
 *  4-4. 약품 넣어서 isPossible함수 실행
 * 5. 출력
 */
public class Solution {
	static int testCase;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int d, w, k;
	static int[][] film, copyFilm;
	static int minChange;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			//1. 높이 d, 너비 w, 합격기준 k 입력
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			//2. film 배열 입력
			film = new int[d][w];
			for(int row = 0; row < d; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col = 0; col < w; col++) {
					film[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
            //3. k == 1이면 무조건 통과
            if (k == 1 || isPossible(film)) {
            	sb.append("#").append(tc).append(' ').append(0).append('\n');
                continue;
            }
            
            minChange = Integer.MAX_VALUE;

            //4. 2중 for문을 돌리는데 첫번째 반복변수는 A를 바꾸는 경우의 수를 비트로 표현, 두번째 반복변수는 B를 바꾸는 경우의 수를 비트로 표현
            int max = 1 << d;
            for (int aMask = 0; aMask < max; aMask++) {
                for (int bMask = 0; bMask < max; bMask++) {
                	//4-1. 만약에 둘이 & 연산한게 0이 아니면 겹치는 걸 바꾸는 거기 때문에 세지 않는다.
                    if ((aMask & bMask) != 0)
                    	continue;

                    //4-2. 그렇게 해서 최소 변환 횟수를 계산한다.
                    int changeCount = Integer.bitCount(aMask | bMask);
                    if (changeCount >= minChange)
                    	continue;

                    //4-3. 복사본을 만들기
                    copyFilm = new int[d][w];
                    for (int i = 0; i < d; i++) {
                    	copyFilm[i] = film[i].clone();
                    }

                    //4-4. 약품 넣어서 isPossible함수 실행
                    for (int i = 0; i < d; i++) {
                        if (((aMask >> i) & 1) == 1) {
                            Arrays.fill(copyFilm[i], 0);
                        } else if (((bMask >> i) & 1) == 1) {
                            Arrays.fill(copyFilm[i], 1);
                        }
                    }

                    if (isPossible(copyFilm)) {
                        minChange = changeCount;
                    }
                }
            }
            //5. 출력
        	sb.append("#").append(tc).append(' ').append(minChange).append('\n');
		}
		System.out.println(sb);
	}
	
	public static boolean isPossible(int[][] film) {
		for(int col = 0; col < w; col++){
			int maxCnt = 1, cnt = 1;
			for(int row = 1; row < d; row++) {
				if(film[row][col] == film[row - 1][col]) {
					cnt++;
				} else cnt = 1;
				maxCnt = Math.max(maxCnt, cnt);
			}
			if(maxCnt < k) return false;
		}
		return true;
	}
}
