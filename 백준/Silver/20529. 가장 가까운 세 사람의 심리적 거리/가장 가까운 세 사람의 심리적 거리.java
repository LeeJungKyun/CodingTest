import java.io.*;
import java.util.*;


public class Main {
	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int testCase;
	static int n;
	static char[][] mbti;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		//코드 작성 시작
		testCase = Integer.parseInt(br.readLine());
		
		while(testCase-- > 0) {
			n = Integer.parseInt(br.readLine());
			if(n > 32) {
                br.readLine();
				System.out.println(0);
				continue;
			}
			mbti = new char[n][4];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				String str = st.nextToken();
				for(int j = 0; j < 4; j++) {
					mbti[i][j] = str.charAt(j);
				}
			}
			//MBTI 입력 끝
			int minDistance = MAX;
			for(int first = 0; first < n; first++) {
				for(int second = first + 1; second < n; second++) {
					for(int third = second + 1; third < n; third++) {
						int curDistance = 0;
						for(int i = 0; i < 4; i++) {
							curDistance += mbti[first][i] != mbti[second][i] ? 1 : 0;
							curDistance += mbti[first][i] != mbti[third][i] ? 1 : 0;
							curDistance += mbti[second][i] != mbti[third][i] ? 1 : 0;
						}
						minDistance = Math.min(curDistance, minDistance);
						if(minDistance == 0) break;
					}
				}
			}
			System.out.println(minDistance);
		}
	}
}